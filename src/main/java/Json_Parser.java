/**
 * Created by admin on 2017. 9. 25..
 */
public abstract class Json_Parser {
    public abstract void apiParserSearch();
    public Json_Parser(){
        try{
            new Json_Parser_Region().apiParserSearch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
