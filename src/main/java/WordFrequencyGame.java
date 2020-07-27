import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE_DELIMITER = "\n";

    public String getResult(String sentence) {
        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] words = sentence.split(SPACE_PATTERN);
                List<WordInfos> wordInfos = new ArrayList<>();
                for (String s : words) {
                    WordInfos input = new WordInfos(s, 1);
                    wordInfos.add(input);
                }
                Map<String, List<WordInfos>> map = getListMap(wordInfos);
                List<WordInfos> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfos>> entry : map.entrySet()) {
                    WordInfos input = new WordInfos(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfos = list;

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordInfos w : wordInfos) {
                    String s = w.getValue() + BLANK_SPACE + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordInfos>> getListMap(List<WordInfos> inputList) {
        Map<String, List<WordInfos>> map = new HashMap<>();
        for (WordInfos input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
