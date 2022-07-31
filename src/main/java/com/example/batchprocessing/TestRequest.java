package com.example.batchprocessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestRequest {
    public List<String> test() {
        return new ArrayList<>(Arrays.asList("0", "1"));
    }
}
