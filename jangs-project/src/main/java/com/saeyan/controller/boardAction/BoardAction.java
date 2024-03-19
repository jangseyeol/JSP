//비슷한 형태의 클래스들로 유사한 작업을 할 때 클래스마다 완전히 다른 이름의 메소드로 접근해서 작업을 하도록 하는 것보다는 동일한 이름의 메소드로 접근할 수 있도록 
//하는 것이 개발자에게 편의 제공한다.
//이렇게 사용 방법은 한 가지(메소드 이름이 동일)인데 구체적인 기능이 다양하도록 설계해놓으면 유사한 작업을 하는 클래스들은 같은 방식으로 사용할 수 있어
//한 번 클래스를 익혀 놓으면 다른 클래스도 쉽게 접근해서 사용할 수 있게 된다.
//이를 다형성이라고 한다.
//다형성을 위해서 비슷한 형태의 클래스를 여러 개 정의해야 할 경우 클래스들 사이의 공통적으로 갖는 메소드를 인터페이스 내의 추상 메소드로 정의해 놓으면
//이를 상속 받는 서브 클래스에서 오버라이딩 하지 않으면 컴파일 에러가 바ㄹ생하기 때문에 강제로 동일한 접근 방식을 취할 수 있다.
//컨트롤러에서 요청이 들어오면 작업에 알맞은 모델 내의 비즈니스 로직이 수행되도록 해야하기 때문에
//컨트롤러에서 동일한 방식으로 모델을 접근할 수 있게 하기 위해서는 이들 모델들이 상속 받아야 하는 인터페이스를 설계해두고 이를 상속받아 사용하도록 한다.
//컨트롤러에서 요청이 들어오면 한 가지 방식으로 모델 내의 비즈니스 로직이 수행되도록 하기 위한 추상 메소드를 인터페이스(Action)에 정의한다.
//인터페이스의 이름은 요청에 의해 동작(액션)이 일어난다는 의미로 Action 이라 준다.

package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	// 요청 파라미터를 동일한 메소드로 처리하도록 하기 위한 추상 메소드를 정의한다.
}
