package com.example.batchprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.internal.org.jline.utils.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<InputAttractionEntity,Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

//    @Override
//    public Person process(final Person person) throws Exception {
//        Document doc = Jsoup.connect("https://disneyreal.asumirai.info/realtime/disneysea-wait-today.html").get();
//        Elements elements = doc.select("table.realtime tbody tr td");
//        Element secondDiv = elements.get(1);
//        for (Element element: elements) {
//            // 取得した要素を出力する
//            Elements time =  element.select("ul li.time p");
//            String testText =  time.text();
//            log.info(element.text());
//        }
////        final String firstName = person.getFirstName();
////        final String lastName = person.getLastName();
////
////        final Person transFarmedPerson = new Person(firstName,lastName);
////
////        log.info("Converting (" + person + ") into (" + transFarmedPerson + ")");
//
//
//
//
//        return null;
//    }

    @Override
    public Person process(InputAttractionEntity item) throws Exception {
        System.out.println();
        return null;
    }

    private List<InputAttractionEntity> readAttraction(){
        List<InputAttractionEntity> attractionEntityList = new ArrayList<>();
        try{
            InputAttractionEntity inputAttractionEntity = new InputAttractionEntity();
            Document disneySeaHtml = Jsoup.connect("https://disneyreal.asumirai.info/realtime/disneysea-wait-today.html").get();
            Elements attractions = disneySeaHtml.select("table.realtime tbody tr td");
            for(int i=0; i<attractions.size();i++){
                // 取得した要素を出力する
                String testText =  attractions.get(i).text();
                attractionEntityList.add(inputAttractionEntity.setInputEntity(attractions.get(i)));
            }
        }catch (IOException e){
            Log.warn(e.getMessage());
            return null;
        }
        return attractionEntityList;
    }
}
