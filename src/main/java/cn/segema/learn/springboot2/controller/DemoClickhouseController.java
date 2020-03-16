//package cn.segema.cloud.demo.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.segema.cloud.demo.domain.DemoHttpDetail;
//import cn.segema.cloud.demo.repository.DemoClickhouseRepository;
//import cn.segema.cloud.demo.utils.ClickhouseQueryUtil;
//import cn.segema.cloud.demo.vo.DemoCarTransactionVO;
//
//@RestController
//@RequestMapping(value = "/demo/clickhouse")
//public class DemoClickhouseController {
//  
//  @Autowired
//  private DemoClickhouseRepository demoClickhouseRepository;
//  
//  @Autowired
//  private ClickhouseQueryUtil clickhouseQueryUtil;
//  
//  
//  @GetMapping("/save")
//	public String save() {
//		DemoCarTransactionVO carsTransactionsVO = new DemoCarTransactionVO();
//		carsTransactionsVO.setPrice(123);
//		carsTransactionsVO.setColor("red");
//		carsTransactionsVO.setMake("HONDA");
//		carsTransactionsVO.setSold(new Date());
//		//clickhouseTestService.save(carsTransactionsVO);
//		return "success";
//	}
//
//	@GetMapping("/delete")
//	public String delete(Integer id) {
//		//clickhouseTestService.deleteById(id);
//		return "success";
//	}
//
//	@GetMapping("/update")
//	public String update(Integer id, String name, String description) {
//		DemoCarTransactionVO carsTransactionsVO = new DemoCarTransactionVO();
//		//clickhouseTestService.save(carsTransactionsVO);
//		return "success";
//	}
//	
//	@GetMapping("/list")
//	public ResponseEntity list() {
//		String sql ="SELECT * FROM NTA_DB.HTTP_LOG WHERE log_date>='2019-03-10' and log_date<='2019-03-14' and url like '%http://img.zsj18.net/favicon.ico%'";
//		
//		demoClickhouseRepository.exeDruidSql(sql);
//		
//		return new ResponseEntity(null);
//	}
//  
//	@GetMapping("/test1")
//	public ResponseEntity test1() {
//		String querySql ="SELECT * FROM NTA_DB.HTTP_LOG WHERE log_date>='2019-03-10' and log_date<='2019-03-14' and url like '%http://img.zsj18.net/favicon.ico%'";
//		
//		Object[] params={};
//		List<DemoHttpDetail> list = new ArrayList<DemoHttpDetail>();
//		try {
//			System.out.println("start:"+System.currentTimeMillis());
//			 list = (List<DemoHttpDetail>)clickhouseQueryUtil.listQuery(querySql, params, DemoHttpDetail.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("end:"+System.currentTimeMillis());
//		}
//		
//		return new ResponseEntity(list,HttpStatus.OK);
//	}
//  
//}
