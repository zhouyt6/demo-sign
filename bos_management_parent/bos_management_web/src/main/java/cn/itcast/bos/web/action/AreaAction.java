package cn.itcast.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimeType;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.AreaService;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.utils.PinYin4jUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@ParentPackage("struts-default")
@Namespace("/")
@Actions
@Controller//相当于bean标签
@Scope("prototype")  //声明多例
public class AreaAction extends BaseAction<Area> {
	
	@Autowired
	private AreaService service;
	
	//添加
	@Action(value="area_add",results={@Result(name="success",location="/pages/base/area.jsp")})
	public String add() {
//		service.add(model);
		return super.SUCCESS;
	}
	//定义上传文件对象
	private File aredFile;
	
	public void setAredFile(File aredFile) {
		this.aredFile = aredFile;
	}
	//excl文件读取上传
	@Action(value="areaAction_importXls")
	public String importXls() throws Exception {
		String flag="1";  //成功或者失败的标志 1-成功 ;0-失败
		try {
			//使用poi读取excel数据并打印
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(aredFile));
			//1.使用workbook读取整个文档对象
			//2.从workbook页中读取sheet页
			List<Area> list = new ArrayList<Area>();
			HSSFSheet sheet = wb.getSheetAt(0);    //本例shee页只有一页直接使用getSheetAt(0)获取
			//3.循环从sheet中读取row行
			for (Row row : sheet) { 
				if (row.getRowNum()==0) {  //跳过第一行标题
					continue;
				}
				//4.读取row中的cell单元格
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				//5.封装到实体类中
				Area area = new Area();
				area.setId(id);
				area.setProvince(province);
				area.setCity(city);
				area.setDistrict(district);
				area.setPostcode(postcode);
				
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				//获取简码
				String tempStr = province + city + district;  //河北石家庄开发
				String[] headByString = PinYin4jUtils.getHeadByString(tempStr); //[H,B,S,J,Z,K,F]
				//去掉中间的,号
				String shortcode = StringUtils.join(headByString);
				area.setShortcode(shortcode);
				//获取城市码
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				area.setCitycode(citycode);
				
				//6.实体放到list集合中;
				list.add(area);
			}
			//6.保存数据
			service.save(list);
		}  catch (IOException e) {
			flag = "0";
			e.printStackTrace();
		}
		//7.将标志位返回 
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return super.NONE;
	}
		
	//区域信息导出为pdf文件
	@Action(value="areaAction_exportPDF")
	public String exportPDF() throws Exception {
		//1.查询所有的区域信息
		List<Area> list = service.findAll();
		//2.设置响应参数和文件名
		String filename = "区域数据报表.pdf";
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		//2.1  	一个流
		ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
		//2.2  	两个头之一:content-type  文件类型
		ServletActionContext.getResponse().setContentType(mimeType );
		//2.3 	两个头之一:content-disposition  告诉浏览器用什么方式打开文件(设置为附件)
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		
		//3.使用jasperreport生成pdf文件
		//读取jrxm文件
		String jrxml = ServletActionContext.getServletContext().getRealPath("/WEB-INF/templates/area2.jrxml");
		//准备需要数据
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("company", "传智播客");
		
		JasperReport report = JasperCompileManager.compileReport(jrxml);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(list));
		//使用JRPdfExproter导出器导出pdf
		//4.通过response将pdf返回到前台
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); //要导出的pdf文件
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		exporter.exportReport();  //导出
		os.close();  // 关闭流
		return NONE;
	}
	
	/**
	 * 分页数据查询
	 */
	@Action(value="area_pageQuery")
	public String pageQuery(){
		//1.封装分页查询的参数  
		//参数1：当前页-1
		//参数2： 每页条数
		Pageable pageable = new PageRequest(page-1, rows);
		
		Specification<Area> spec = new Specification<Area>() {
			
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<>();
				
				String province = model.getProvince();
				if (StringUtils.isNotBlank(province)) {  //省
					list.add(cb.like(root.get("province").as(String.class), "%"+province+"%"));
				}
				
				String city = model.getCity();
				if (StringUtils.isNotBlank(city)) {  //city市
					list.add(cb.like(root.get("city").as(String.class), "%"+city+"%"));
				}
				
				String district = model.getDistrict();
				if (StringUtils.isNotBlank(district)) {   //district 区
					list.add(cb.like(root.get("district").as(String.class), "%"+district+"%"));
				}
				
				//创建条件数组.长度为list集合的长度
				Predicate[] pre = new Predicate[list.size()];
				return query.where(list.toArray(pre)).getRestriction();
			}
		};
 		//2.执行分页查询（总条数和每页数据的集合）
		Page<Area> page = service.pageQuery(spec,pageable);
		//3.将查询结果转成json数据
		String[] excludes = {"subareas"};
		super.page2JsonObject(page, excludes);  //调用父类的方法转化为json数据
		return super.NONE;
	}
	@Action(value="area_findAll")
	public String findAll(){
		//1.查询所有的取派标准数据
		List<Area> list = service.findAll();
		//2.将数据转换成json，返回到前台
		String[] excludes = {"subareas"};
		super.page2JsonArray(list, excludes);  //调用父类的方法转化为json数据
		return super.NONE;
	}

}
