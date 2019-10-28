import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChapterOne {
    //get argument
    public static void main(String[] args) {
        System.out.println("Chapter 1");

        String wordlistFile = "wordlist.txt";
        String sourceWord = "documenting";
        Anagram anagram = new Anagram(sourceWord);
        List<String> wordList;

        try {
            wordList = Arrays.asList(anagram.getFilecontents(wordlistFile));
            List<String> result = anagram.findAnagram(wordList);
            System.out.println("RESULT");

            if(result == null) {
                System.out.println("NOTFOUND");

            } else {
                for(String words: result) {
                    System.out.println(words);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Anagram {

    String sourceWord;

    public Anagram(String sourceWord) {
        super();
        this.sourceWord = sourceWord;
    }

    public String[] getFilecontents(String wordlistFile) throws IOException {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();

        try {
          reader =  new BufferedReader( new FileReader(wordlistFile));
          String line = reader.readLine();

          while(line != null) {
              line = line.trim().replaceAll("\\s+", " ");
              sb.append(line);
              sb.append(" ");
              line = reader.readLine();
          }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return sb.toString().split(" ");

    }

    public List<String> findAnagram(List<String> wordList) {
       String sortedSource = sortString(this.sourceWord);
       List<String> result = new ArrayList<>();
       System.out.println("source : " + sortedSource);

       //make two-word combination and compare check if the word is an anagram of documenting

        for(String word1: wordList) {
            for(String word2: wordList) {
                String combination = word1 + word2;

                if(wordList.indexOf(word1) > wordList.indexOf(word2)
                        || combination.length() != sortedSource.length() ) {
                    continue;
                }

                String compareString = sortString(word1+word2);

                if(sortedSource.equals(compareString)) {
                    result.add(combination);
                    System.out.println("EUREKA!!!!! :" + word1 + " " + word2);
                }
            }
        }

        return result;
    }

    private static String sortString(String word) {
        //change string to array using sort function in array.
       char[] charArray = word.toCharArray();
       Arrays.sort(charArray);

       return new String(charArray);
    }

}
