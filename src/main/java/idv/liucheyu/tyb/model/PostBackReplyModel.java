package idv.liucheyu.tyb.model;


public class PostBackReplyModel implements PostBackModel {
    private String replyToken;

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }
}
