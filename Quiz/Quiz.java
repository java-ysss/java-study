package Quiz;

//問題・選択肢・答えのデータ

public class Quiz {
    private String question;
    private String select1;
    private String select2;
    private String select3;
    private String select4;
    private int answer;
    private String hint;

    public Quiz(String question,String select1, String select2, String select3 ,String select4, int answer,String hint){
        this.question = question;
        this.select1 = select1;
        this.select2 = select2;
        this.select3 = select3;
        this.select4 = select4;
        this.answer = answer;
        this.hint = hint;
    }

    public String getQuestion(){
        return question;
    }
    public String getSelect1(){
        return select1;
    }
    public String getSelect2(){
        return select2;
    }
    public String getSelect3(){
        return select3;
    }
    public String getSelect4(){
        return select4;
    }
    public int getAnswer(){
        return answer;
    }
    public String getHint(){
        return hint;
    }
}
