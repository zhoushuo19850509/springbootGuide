package com.example.restreactive.client;

public class StartClient {
    public static void main(String[] args){
        System.out.println("hello");
		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getResult());
    }
}
