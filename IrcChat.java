import enigma.core.Enigma;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IrcChat {

    public static void main(String[] args) throws IOException{
        enigma.console.Console cn = Enigma.getConsole("IRC CHAT", 70, 25, 17);

        BufferedReader reader = new BufferedReader(new FileReader("chat.txt"));
        String line = reader.readLine();
        SingleLinkedList lines = new SingleLinkedList();


        while (line != null) { // Add the current line into stack until there is no new line
            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
        SingleLinkedListNode temp = lines.head;
        String[] words = new String[lines.size()];
        EnigmaWrapper ew = new EnigmaWrapper(cn);

        while(temp != null){
            words = ((String)temp.getData()).split("_");   // [time] [<@id>] [text]

            Color color = new Color(189, 147, 255);
            ew.printInColor(color, "[" + words[0] + "] ");

            color = new Color(255, 121, 198);
            ew.printInColor(color, "<@");
            System.out.print(words[1]);
            ew.printInColor(color, ">  ");
            System.out.println(words[2]);
            temp = temp.getLink();
        }
    }
}
