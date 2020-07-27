import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE_DELIMITER = "\n";

    public String getResult(String sentence) {

            try {
                List<WordInfos> wordInfos = calculateWordFrequency(sentence);

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordInfos wordInfo : wordInfos) {
                    String wordInfoSentence = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
                    joiner.add(wordInfoSentence);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
    }

    private List<WordInfos> calculateWordFrequency(String sentence) {
        List<WordInfos> wordInfos=new ArrayList<>();
        List<String> words= Arrays.asList(sentence.split(SPACE_PATTERN));
        for(String uniqueWord : new HashSet<>(words)){
            int count= (int) words.stream().filter(word -> word.equals(uniqueWord)).count();
            wordInfos.add(new WordInfos(uniqueWord,count));
        }
        return wordInfos;
    }

    private Map<String, List<WordInfos>> getListMap(List<WordInfos> inputList) {
        Map<String, List<WordInfos>> map = new HashMap<>();
        for (WordInfos input : inputList) {
            if (!map.containsKey(input.getValue())) {
                List<WordInfos> arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
