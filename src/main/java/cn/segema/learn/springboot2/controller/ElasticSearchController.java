//package cn.segema.learn.springboot2.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.segema.learn.springboot2.repository.CarTransactionRepository;
//import cn.segema.learn.springboot2.vo.CarTransactionVO;
//
//@RestController
//@RequestMapping(value = "/elasticsearch")
//public class ElasticSearchController {
//
//	@Resource
//    private CarTransactionRepository carTransactionRepository;
//    
//	@Resource
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @PostMapping
//    public ResponseEntity add(@RequestBody CarTransactionVO carTransaction) {
//        carTransaction.setId(UUID.randomUUID().toString());
//        carTransaction.setSold(new Date());
//        carTransactionRepository.save(carTransaction);
//        return new ResponseEntity(carTransaction, HttpStatus.OK);
//    }
//
//    @PostMapping("/batch")
//    public ResponseEntity addBatch() {
//        List<CarTransactionVO> list = new ArrayList<CarTransactionVO>();
//        for (int i = 0; i < 10; i++) {
//            CarTransactionVO carsTransactionsVO = new CarTransactionVO();
//            carsTransactionsVO.setPrice(i);
//            carsTransactionsVO.setColor("red" + i);
//            carsTransactionsVO.setMake("HONDA" + i);
//            carsTransactionsVO.setSold(new Date());
//            list.add(carsTransactionsVO);
//        }
//        carTransactionRepository.saveAll(list);
//        return new ResponseEntity(list, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable String id) {
//        carTransactionRepository.deleteById(id);
//        return new ResponseEntity("刪除成功", HttpStatus.OK);
//
//    }
//
//    @PutMapping
//    public ResponseEntity update(@RequestBody CarTransactionVO carTransaction) {
//        carTransactionRepository.save(carTransaction);
//        return new ResponseEntity("success", HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity findById(@PathVariable String id) {
//        Optional<CarTransactionVO> carsTransactionsVO = carTransactionRepository.findById(id);
//        return new ResponseEntity(carsTransactionsVO, HttpStatus.OK);
//    }
//
//    @GetMapping("/list/{color}/{make}")
//    public ResponseEntity findListByColorMake(@PathVariable String color,@PathVariable String make) {
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if (color != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("color", color));
//        }
//        if (make != null) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("make", make));
//        }
//        Iterable<CarTransactionVO> carsTransactionsList = carTransactionRepository.search(boolQueryBuilder);
//        return new ResponseEntity(carsTransactionsList, HttpStatus.OK);
//    }
//    
//    @PostMapping("/template")
//    public ResponseEntity addByTempalte(@RequestBody CarTransactionVO carTransaction) {
//        carTransaction.setId(UUID.randomUUID().toString());
//        carTransaction.setSold(new Date());
//        //elasticsearchTemplate.putMapping("car", "transaction", carTransaction);
//        elasticsearchTemplate.createIndex("student", carTransaction);
//        return new ResponseEntity(carTransaction, HttpStatus.OK);
//    }
//
//    @GetMapping("/template")
//    public ResponseEntity findByTempalte(CarTransactionVO carTransaction) {
//        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
//        List<CarTransactionVO> carTransactions = elasticsearchTemplate.queryForList(searchQuery, CarTransactionVO.class);
//        return new ResponseEntity(carTransactions, HttpStatus.OK);
//    }
//}
