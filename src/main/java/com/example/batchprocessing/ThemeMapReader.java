package com.example.batchprocessing;

import com.sun.java.accessibility.util.internal.ListTranslator;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemeMapReader implements ItemReader<InputAttractionEntity> {
    TestRequest testRequest;
    private List<InputAttractionEntity> attractionEntityList =null;
    private int nextIndex;
//    private InputAttractionEntity inputAttractionEntity;

//    public InputAttractionEntity create(){
//        try{
//            InputAttractionEntity inputAttractionEntity = new InputAttractionEntity();
//            Document disneySeaHtml = Jsoup.connect("https://disneyreal.asumirai.info/realtime/disneysea-wait-today.html").get();
//            Elements attractions = disneySeaHtml.select("table.realtime tbody tr td");
//            for (Element attraction: attractions) {
//                // 取得した要素を出力する
//                InputAttractionEntity attractionInformation = inputAttractionEntity.setInputEntity(attraction);
//                return attractionInformation;
//            }
//        }catch (IOException e){
//            Log.warn(e.getMessage());
//            return null;
//        }
//        return null;
//
//        int a = 3;
//
//        return new FlatFileItemReaderBuilder<Person>()
//                //単純な名前の指定
//                .name("personItemReader")
//                .resource(new ClassPathResource("sample-data.csv"))
//                .delimited()
//                .names(new String[]{"firstName","lastName"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
//                    setTargetType(Person.class);
//                }
//                }).build();
//    }



    @Override
    public InputAttractionEntity read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(attractionEntityList == null){
            try{
                // listを初期化
                attractionEntityList = new ArrayList<>();
                InputAttractionEntity inputAttractionEntity = new InputAttractionEntity();
                Document disneySeaHtml = Jsoup.connect("https://disneyreal.asumirai.info/realtime/disneysea-wait-today.html").get();
                Elements attractions = disneySeaHtml.select("table.realtime tbody tr td");
                for (Element attraction : attractions) {
                    // 取得した要素を出力する
                    attractionEntityList.add(inputAttractionEntity.setInputEntity(attraction));
                }
            }catch (IOException e){
                Log.warn(e.getMessage());
                return null;
            }
            nextIndex = 0;
        }
        InputAttractionEntity attractionEntity = null;
        if(nextIndex < attractionEntityList.size()){
            attractionEntity = attractionEntityList.get(nextIndex);
            nextIndex++;
        }

        return attractionEntity;
    }
}
