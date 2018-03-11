package guru.springframe.didemo.filtering;

import guru.springframe.didemo.messaging.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.matchers.ArrayEquals;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AudCurrencyFilterTest {

    @Test
    public void doFilter() {

        Message message1 = new Message("AUD", "transaction_created", "abc111");
        Message message2 = new Message("AUD", "transaction_reverse", "abc112");
        Message message3 = new Message("GBP", "transaction_created", "abc113");
        Message message4 = new Message("GBP", "transaction_reverse", "abc114");

        List<Message> messages = new ArrayList<Message>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);

        FilterInterface audCurrencyFilter = new AudCurrencyFilter();
        List<Message> audCurrencyMessages = audCurrencyFilter.doFilter(messages);

        Message expectedMessage1 = new Message("AUD", "transaction_created", "abc111");
        Message expectedMessage2 = new Message("AUD", "transaction_reverse", "abc112");
        List<String> expectList = new ArrayList<String>();
        expectList.add(expectedMessage1.toString());
        expectList.add(expectedMessage2.toString());


        List<String> audMessagesString = new ArrayList<String>();
        for (Message message : audCurrencyMessages) {
//            System.out.println("currency: " + message.getCurrency() + " type: " + message.getType() + " source: " + message.getSource());
            audMessagesString.add(message.toString());
        }

//        System.out.println(" ");
//
//        for (Message expectMessage : expectList) {
//            System.out.println("currency: " + expectMessage.getCurrency() + " type: " + expectMessage.getType() + " source: " + expectMessage.getSource());
//        }

//        assertEquals(audCurrencyMessages, expectList);

//        List<String> expect = new ArrayList<String>();
//        expect.add("aaa");
//        expect.add("bbb");
//
//        List<String> actual = new ArrayList<String>();
//        actual.add("aaa");
//        actual.add("bbb");

        assertEquals(audMessagesString, expectList);
    }
}