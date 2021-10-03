package idv.liucheyu.tyb.controller;

import idv.liucheyu.tyb.annotation.PostBackComponent;
import idv.liucheyu.tyb.annotation.PostBackMethod;
import idv.liucheyu.tyb.model.BankModel;
import idv.liucheyu.tyb.model.PayModel;

@PostBackComponent
public class PostBackHandler {

    @PostBackMethod
    public void postBackAction(PayModel postBackData){
        System.out.println(postBackData);
    }

    @PostBackMethod
    public void postBackAction(BankModel postBackData) {
        System.out.println(postBackData);
    }

}
