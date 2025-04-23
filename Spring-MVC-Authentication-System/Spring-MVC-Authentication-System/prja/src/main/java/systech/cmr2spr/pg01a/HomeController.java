package systech.cmr2spr.pg01a;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	@RequestMapping("/pg01a.ctrl.home")
	public String home() {
		return "pg01a.view.home"; //pg01a.view.home.jsp
	}
}
