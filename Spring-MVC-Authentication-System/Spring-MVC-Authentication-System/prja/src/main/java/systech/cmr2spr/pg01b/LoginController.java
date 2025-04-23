package systech.cmr2spr.pg01b;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
@Controller
public class LoginController {
  private static Dictionary<String, LoginModel> dlm=new Hashtable<>();
  @RequestMapping("/pg01b.ctrl.home")
  public String home(HttpServletRequest req, Model mdl) {
    return logout(req, mdl);
  }
  @RequestMapping("pg01b.ctrl.signin")
  public String login() {
    return "pg01b.view.signin"; // pg01b.view.signin.jsp
  }
  @RequestMapping("/pg01b.ctrl.signup")
  public String register() {
    return "pg01b.view.signup"; // pg01b.view.signup.jsp
  }
  @RequestMapping("/pg01b.ctrl.adduser")
  public String addNewUser(HttpServletRequest req, Model mdl) {
    String uid=req.getParameter("tbxuid");
    String upwd=req.getParameter("tbxupwd");
    try {
      LoginModel t=new LoginModel(uid, upwd);
      if (exist(t, false) != null) {
        throw new Exception("User already exist");
      }
      dlm.put(uid, t);
// mdl.addAttribute("abLLm", dlm);
      mdl.addAttribute("msg", "Create:Sign-up");
    } catch (Exception e) {
      System.out.println("err.:" + e.getMessage());
      mdl.addAttribute("msg", "err.:" + e.getMessage());
      return "pg01b.view.signup";
    }
    return "pg01b.view.signin";
  }
  @RequestMapping("/pg01b.ctrl.module1")
  public String toModulel(HttpServletRequest req, Model mdl) {
    String uid=req.getParameter("tbxuid");
    String upwd=req.getParameter("tbxupwd");
    try {
      LoginModel t=exist(new LoginModel(uid, upwd), false);
      if (t == null) {
        throw new Exception("Invalid User ID or Password");
      }

      t = exist(new LoginModel(uid, upwd, true), true);
      if (t != null) {
        throw new Exception("Already User Login");
      }
      if (updateLogin(new LoginModel(uid, upwd, true))) {
        mdl.addAttribute("uid", uid);
        mdl.addAttribute("upwd", upwd);
        return "pg01b.view.module1"; // pg01b.view.module1.jsp
      }

    } catch (Exception e) {
      System.out.println("err.:" + e.getMessage());
      mdl.addAttribute("msg", "err.:" + e.getMessage());
    }
    return "pg01b.view.signin";
  }
  @RequestMapping("/pg01b.ctrl.signout")
  public String logout(HttpServletRequest req, Model mdl) {
    String uid=req.getParameter("hdnuid");
    String upwd=req.getParameter("hdnupwd");
    try {
      LoginModel t=exist(new LoginModel(uid, upwd), false);
      if (t == null) {
        throw new Exception("Invalid User ID or Password");
      }

      t=exist(new LoginModel(uid, upwd, true), true);
      if (t == null) {
        throw new Exception("User not Sign-In");
      }
      if (updateLogin(new LoginModel(uid, upwd, false))) {
        return "pg01b.view.signin";
      }
    } catch (Exception e) {
      mdl.addAttribute("msg", "err.:" + e.getMessage());
      System.out.println("err.:" + e.getMessage());
    }
    return "pg01b.view.home"; // pgela.view.home.jsp
  }
  @RequestMapping("/pg0lb.ctrl.updateuser")
  public String edituser(HttpServletRequest req, Model mdl) {
    String huid=req.getParameter("hdnuid");
    String hupwd=req.getParameter("hdnupwd");
    String uid=req.getParameter("tbxuid");
    String upwdx=req.getParameter("tbxupwdx").trim();
    String upwdn=req.getParameter("tbxupwdn").trim();
    try {
      boolean bl=upwdx.equalsIgnoreCase(upwdn);
      boolean b2=upwdx.length() < 3;
      boolean b3=upwdn.length() < 3;
      boolean b4=huid.equalsIgnoreCase(uid) == false;
      boolean b5=hupwd.equalsIgnoreCase(upwdx) == false;
      if (bl || b2 || b3 || b4 || b5) {
        mdl.addAttribute("uid", huid);
        mdl.addAttribute("upwd", hupwd);
        mdl.addAttribute("msg", "err.:Invalid password");
        return "pg01b.view.module1";
      }
      dlm.put(huid, new LoginModel(huid, upwdn, true));
      mdl.addAttribute("uid", huid);
      mdl.addAttribute("upwd", upwdn);
      mdl.addAttribute("msg", "Update: Password");
      return "pg01b.view.module1";
    } catch (Exception e) {
      System.out.println("err.:" + e.getMessage());
      mdl.addAttribute("msg", "err.:" + e.getMessage());
    }
    return "pg01b.view.signin";
  }
  @RequestMapping("/pg0lb.ctrl.deluser")
  public String removeUser(HttpServletRequest req, Model mdl) {
    String uid=req.getParameter("hdnuid");
    String upwd=req.getParameter("hdnupwd");
    try {
      LoginModel t=exist(new LoginModel(uid, upwd, true), true);
      if (t == null) {
        throw new Exception("User not Sign-In");
      }
      t = dlm.remove(uid);
      if (t != null) {
        mdl.addAttribute("msg", "Delete:Sign-up");
        mdl.addAttribute("elm", t);
        return "pg01b.view.signin";
      }
    } catch (Exception e) {
      mdl.addAttribute("msg", "err.:" + e.getMessage());
      System.out.println("err.:" + e.getMessage());
    }
    return "pg01b.view.home";
  }
  @RequestMapping("/pg01b.ctrl.admin")
  public String clients(HttpServletRequest req, Model mdl) {
	  //string table
    String stbl="<table width='100%'><thead>";
    stbl += "<th align='left'>ID";
    stbl += "<th align='left'>Password";
    stbl += "<th align='left'>Status";
    // String table row
    String str="";
    try {
      Enumeration<String> uids=dlm.keys();
      while(uids.hasMoreElements()) { 
        String key=uids.nextElement();
        LoginModel t=dlm.get(key);
        str += "<tr>";
        str += "<td>" + t.getUid(); 
        str += "<td>" + t.getUpwd();
        str += "<td>" +(t.getIsSignIn() ? "Login": "Logout");
      }
      str += "</table>";
    } catch (Exception e) {
      str="</table>";
    }
    stbl += str;
    mdl.addAttribute("atbUsers", stbl);
    return "pg01b.view.users"; //pg01b.view.users.jsp
  }
  private LoginModel exist(LoginModel lm, boolean compareAll) {
    try {
      LoginModel t=dlm.get(lm.getUid());
      System.out.println(lm.getUid());
      System.out.println(lm.getUpwd());
      if (t == null) {
        return null;
      }
      boolean bid=t.getUid().equalsIgnoreCase(lm.getUid()); 
      boolean bpwd=t.getUpwd().equalsIgnoreCase(lm.getUpwd());
      boolean bisi=t.getIsSignIn() == lm.getIsSignIn();
      if (compareAll) {
        if (bid && bpwd && bisi ) {
          return t;
        }
      } else if (bid && bpwd ) {
        return t;
      }
    } catch(Exception e) {
      System.out.println("err.:" + e.getMessage());
    }
    return null;
    }
  private boolean updateLogin(LoginModel lm) {
    try {
      dlm.put(lm.getUid(), new LoginModel(lm.getUid(), lm.getUpwd(), lm.getIsSignIn()));
      lm = dlm.get(lm.getUid());

      return true;
    } catch(Exception e) {
      System.out.println("err.:" + e.getMessage());
    }
    return false;
  }
}