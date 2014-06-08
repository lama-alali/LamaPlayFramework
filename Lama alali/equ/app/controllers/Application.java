package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;

public class Application extends Controller {
	public static class Factors  {
		Double a;
		Double b;
		Double c;
		public Factors(){}
		public Factors(Double a1,Double b1,Double c1){
			a=a1;b=b1;c=c1;
		}
		}

    public static Result index() {
        return ok(index.render(form(Factors.class)));
    }
    public static Result solve() {
    	
        Form<Factors> form = form(Factors.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(index.render(form));
        } else {

        	Factors data=new Factors(Double.valueOf(form.data().get("a")), Double.valueOf(form.data().get("b")),Double.valueOf(form.data().get("c")));
        	Double delta=0.0;
            delta = data.b*data.b - 4*(data.a*data.c);
        	String man;
        	Double x3=0.0,x1=0.0,x2=0.0;
			if(delta<0){man="no solution ";
				return ok(
						hello.render(x1,x2,man));}
        	else if(delta==0){
        		
        		 x1=-data.b/2*data.a;
        		 x2=-data.b/2*data.a;
        		 man ="two equal solutions =one solution ";
        		return ok(
        				hello.render(x1,x2,man));}
        	else
        		 x3 = Math.sqrt(delta);
			x1 =(-data.b-x3)/2*data.a; ;
			x2 = (-data.b+x3)/2*data.a;;
        	man="two different solutions ";
                return ok(hello.render(x1, x2,man));
        }
    }

}
