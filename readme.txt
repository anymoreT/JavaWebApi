1. mvn 运行指定的类或者方法
>mvn test -Dtest=[ClassName]
运行测试类中指定的方法：(这个需要maven-surefire-plugin:2.7.3以上版本才能支持)
 
>mvn test -Dtest=[ClassName]#[MethodName] 
//[MethodName]为要运行的方法名，支持*通配符，范例：
>mvn test -Dtest=MyClassTest#test1
>mvn test -Dtest=MyClassTest#*test*

 mvn test -Dtest=StartWebTest#f1