//package cn.segema.learn.springboot2.aop;
//
//import java.util.Enumeration;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//@Aspect
////@Component
//public class WebLogAspect {
//	private static final Logger logger =  LoggerFactory.getLogger(WebLogAspect.class);
//	
//	/**切面点*/
//    private final String POINT_CUT = "execution(* cn.segema.cloud.demo.controller..*(..))";
//    @Pointcut(POINT_CUT)
//    private void pointcut(){}
//	
////	@Pointcut("excution(*cn.segema.cloud.demo.controller..*(..))")
////	public void webLog() {
////		
////	}
//	
//    /** 
//     * 前置通知，方法调用前被调用 
//     * @param joinPoint 
//     */
//    @Before(value = POINT_CUT)
//	public void doBefore(JoinPoint joinPoint) throws Throwable{
////		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
////		HttpServletRequest request = attributes.getRequest();
////		logger.info("URL:"+request.getRequestURI().toString());
////		logger.info("HTTP_METHOD:"+request.getMethod());
////		logger.info("IP:"+request.getRemoteAddr());
////		Enumeration<String> enu = request.getParameterNames();
////		while(enu.hasMoreElements()) {
////			String name = (String)enu.nextElement();
////			logger.info("name{},value{}:",name,request.getParameter(name));
////		}
//    	logger.info("前置通知");
//        //获取目标方法的参数信息  
//        Object[] obj = joinPoint.getArgs();  
//        //AOP代理类的信息  
//        joinPoint.getThis();  
//        //代理的目标对象  
//        joinPoint.getTarget();  
//        //用的最多 通知的签名  
//        Signature signature = joinPoint.getSignature();  
//        //代理的是哪一个方法  
//        logger.info("代理的是哪一个方法"+signature.getName());  
//        //AOP代理类的名字  
//        logger.info("AOP代理类的名字"+signature.getDeclaringTypeName());  
//        //AOP代理类的类（class）信息  
//        signature.getDeclaringType();  
//        //获取RequestAttributes  
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
//        //从获取RequestAttributes中获取HttpServletRequest的信息  
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
//        //如果要获取Session信息的话，可以这样写：  
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);  
//        //获取请求参数
//        Enumeration<String> enumeration = request.getParameterNames();  
////        Map<String,String> parameterMap = Maps.newHashMap();  
////        while (enumeration.hasMoreElements()){  
////            String parameter = enumeration.nextElement();  
////            parameterMap.put(parameter,request.getParameter(parameter));  
////        }  
////        String str = JSON.toJSONString(parameterMap);  
////        if(obj.length > 0) {  
////            logger.info("请求的参数信息为："+str);
////        }  
//    	
//	}
//	
//	@AfterReturning(value = POINT_CUT,returning = "keys")
//	public void doAfterReturning(JoinPoint joinPoint,Object keys){
//		//处理完毕，返回内容
//		logger.info("RESPONSE:"+keys);
//		
//	}
//
//
//	
//}
