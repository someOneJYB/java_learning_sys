package another;

import org.springframework.context.annotation.Import;

//@Import(HeaderParser.class)
public class HeaderParser {

    public void parse(){
        System.out.println("HeaderParser ... parse ...");
    }

}
