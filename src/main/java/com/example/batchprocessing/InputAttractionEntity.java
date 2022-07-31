package com.example.batchprocessing;

import io.micrometer.core.instrument.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class InputAttractionEntity {
    private String attractionName;
    private int attractionWaitingTime;
    private String nowClosed;

    public InputAttractionEntity(){
    }

    public InputAttractionEntity setInputEntity(Element attraction){
        String attractionName = getAttractionNameForInput(attraction);

        String attractionInformation = attraction.select("ul li.time").text();
        String attractionClosedText =  attraction.select("ul li.desc").text();
        if(StringUtils.isEmpty(attractionInformation)){
            return new InputAttractionEntity(attractionName,-1,"運営中止中");
        }else {
            attractionWaitingTime = Integer.parseInt(attractionInformation.replaceAll("[^0-9]",""));
            return new InputAttractionEntity(attractionName,attractionWaitingTime,"運営中");
        }
    }

    // アトラクション名を取得
    private String getAttractionNameForInput(Element attraction){
        String nameAndWaitTime  = attraction.select("a").first().attr("title");
        //「の待ち時間」を削除する
        String attractionName = nameAndWaitTime.substring(0,nameAndWaitTime.length()-5);
        if(StringUtils.isEmpty(attractionName)){
            Log.warn("アトラクション名を取得できません");
            return "その他";
        }
        return attractionName;
    }

}
