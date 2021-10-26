## @RequestBody
requestBody에 여러 vo를 담고 싶다면 새로운 Vo를 만들어서 각 vo를 넣어준다
~~~java
   @Data
   @Getter @Setter @toString
   public class requestVO {
        private TestVO testVO;
        private TestVO2 testVO2;
        }
~~~
이런식으로 각 VO에 매칭될수 있는 requestVO를 만들고
~~~java
@Controller
public class TestController {
    
    public ResponseEntity<?> test(@RequestBody requestVO vo){
        TestVO testVO = vo.getTestVO;
        TestVO2 testVO2 = vo.getTestVO2;
    }
}
~~~
해당 Controller에서는 이렇게 맵핑하여 사용하면 된다.

또한 Web Application의 구조상 @RequestBody는 Single Object만을 처리할 수 있도록 되어 있기 때문에 여러개의 @RequestBody를 선언하는 것은 불가능하다.

