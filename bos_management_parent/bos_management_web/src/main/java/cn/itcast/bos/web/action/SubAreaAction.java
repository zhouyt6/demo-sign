package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimeType;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.ISubAreaService;
import cn.itcast.bos.utils.FileUtils;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller
@Scope("prototype")
public class SubAreaAction extends BaseAction<SubArea> {
	
	@Resource
	private ISubAreaService subAreaService;
	
	//分页查询所有信息
	@Action(value="subareaAction_pageQuery")
	public String pageQuery() {
		Pageable pageable = new PageRequest(page-1, rows);
		Specification<SubArea> spec = new Specification<SubArea>() {

			@Override
			public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<>();
				Area area = model.getArea();
				if (null != area) {   //省
					String province = area.getProvince();
					if (StringUtils.isNotBlank(province)) {  //省
						 Join<SubArea,Area> join = root.join(root.getModel().getSingularAttribute("area",Area.class));
						 list.add(cb.like(join.get("province").as(String.class), "%"+province+"%"));
					}
					
					String city = area.getCity();
					if (StringUtils.isNotBlank(city)) {  //city市
						 Join<SubArea,Area> join = root.join(root.getModel().getSingularAttribute("area",Area.class));
						 list.add(cb.like(join.get("city").as(String.class), "%"+city+"%"));
					}
					String district = area.getDistrict();
					if (StringUtils.isNotBlank(district)) {   //district 区
						Join<SubArea,Area> join = root.join(root.getModel().getSingularAttribute("area",Area.class));
						list.add(cb.like(join.get("district").as(String.class), "%"+district+"%"));
					}
				}
				
				FixedArea fixedArea =model.getFixedArea();
				if (null != fixedArea) { //定区编码  -------多表查询,左外-JoinType.LEFT可以省略
					String id = fixedArea.getId();
					if(StringUtils.isNotBlank(id)){
						  Join<SubArea,FixedArea> join = root.join(root.getModel().getSingularAttribute("fixedArea",FixedArea.class));
						  list.add(cb.equal(join.get("id").as(String.class), id));
					}
				}
				
				String keyWords = model.getKeyWords();
				if (StringUtils.isNotBlank(keyWords)) {   //关键字
					list.add(cb.equal(root.get("keyWords").as(String.class), "%"+keyWords+"%"));
				}
				
				Predicate[] pre = new Predicate[list.size()];
				return query.where(list.toArray(pre)).getRestriction();
			}
		};
		//执行分页
		 Page<SubArea> page = subAreaService.pageQuery(spec,pageable);
		 String[] excludes ={"fixedArea","subareas"};
		 super.page2JsonObject(page, excludes);
		return NONE;
	}
	
	@Action(value="subareaAction_save",results={@Result(name="success",location="/pages/base/sub_area.jsp")})
	public String subarea_save() {
		subAreaService.save(model);
		return super.SUCCESS;
	}
	
	/**
	 * 	查询所有的分区数据,放到excel表格中,通过response将excel返回到前台
	 * @throws IOException 
	 */
	@Action(value="subareaAction_exportXls")
	public String exportXls() throws IOException {
		//1.查询所有分区
	    List<SubArea> list =subAreaService.findAll();
	    //2.存在分区数据可以导出 ,创建excel对象
	    if (list != null && list.size() > 0) {
			//2.1 创建excel对象
	    	HSSFWorkbook wb = new HSSFWorkbook();
	    	//2.2 创建sheet页
	    	HSSFSheet sheet = wb.createSheet();
	    	//2.3 在sheet页中创建标题行(在第一行是标题头)
	    	HSSFRow row = sheet.createRow(0);  //创建第一行,编号从0开始
	    	//2.4 在标题行创建标题单元格
	    	row.createCell(0).setCellValue("分区编号");
	    	row.createCell(1).setCellValue("分区地址");
	    	row.createCell(2).setCellValue("分区关键字");
	    	row.createCell(3).setCellValue("分区辅助关键字");
	    	row.createCell(4).setCellValue("区域编号");
    	//3.到此则存在分区数据, 循环将数据保存到excel表格中
	    	int index = 1;
	    	for (SubArea subArea : list) {
				//3.1 循环创建行
	    		row = sheet.createRow(index++);
	    		//3.2 创建行的列,给列赋值
	    		row.createCell(0).setCellValue(subArea.getId());
	    		row.createCell(1).setCellValue(subArea.getAddress());
	    		row.createCell(2).setCellValue(subArea.getKeyWords());
	    		row.createCell(3).setCellValue(subArea.getAssistKeyWords());
	    		Area area = subArea.getArea(); //获取分区的区域
	    		if (area != null) { 
	    			//给分区绑定了区域
	    			row.createCell(4).setCellValue(subArea.getId());
				} else { 
					//分区未指定区域
					row.createCell(4).setCellValue("分区未指定区域");
				}
			}
    	//4.设置response 的响应参数:  一个流两个头
	    	String filename = "区域信息.xls";  //保存的文件名
	    	//获取浏览器类型
	    	String agent = ServletActionContext.getRequest().getHeader("User-Agent");
	    	String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
	    	//根据浏览器类型对文件名进行编码
	    	filename = FileUtils.encodeDownloadFilename(filename, agent);
	    	//4.1 一个流,response的输出流
	    	ServletOutputStream sos = ServletActionContext.getResponse().getOutputStream();
			//4.2 两个头之一:content-type :告诉浏览器返回的的数据的格式
	    	ServletActionContext.getResponse().setContentType(mimeType);
	    	/**
	    	 * 4.3 两个头之二:content-disposition,告诉前台浏览器数据的打开方式;
	    	 *  附件方式打开值为: attachment;filename=文件名
	    	 */
	    	ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
    	//5.通过response将excel返回到前台
	    	wb.write(sos);
		}
		return NONE;
	}

	//显示分区分布图
	@Action(value="subareaAction_showChart")
	public String showChart() {
		//1.查询所有分区数据省份和省份下的分区个数
		List<Object> list = subAreaService.findGroupedProvincesAndNum();
		String[] excludes = {};
		//2.将查询的数据转成json数组返回到前台
		page2JsonArray(list, excludes );
		return NONE;
	}
	
	/**
	 * 查询关联和未关联定区的分区
	 */
	@Action(value="subAreaAction_findNoAssociateSubArea")
	public String findNoAssociateSubArea(){
		List<SubArea> list = subAreaService.findNoAssociateSubArea();
		String[] excludes = {"fixedArea","area"};
		page2JsonArray(list, excludes );
		return NONE;
	} 
	@Action(value="subAreaAction_findAssociateSubArea")
	public String findAssociateSubArea(){
		List<SubArea> list = subAreaService.findAssociateSubArea();
		String[] excludes = {"fixedArea","area"};
		page2JsonArray(list, excludes );
		return NONE;
	} 
}
