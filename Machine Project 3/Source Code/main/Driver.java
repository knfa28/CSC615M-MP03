package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        MP03 program = readProgram("src/main/program.txt");
        //program.printStates();
        ArrayList<String> inputs = readInput("src/main/input.txt");
        
        for(int i = 0; i < inputs.size(); i++){
            if(!inputs.get(i).isEmpty()){
                if(program.process(inputs.get(i)))
                    System.out.println(inputs.get(i) + " is ACCEPTED!");
                else
                    System.out.println(inputs.get(i) + " is REJECTED!");
            } else
                System.out.println("Input file is empty!");
        }
    }
    
    public static MP03 readProgram(String filename){        
        Path path = Paths.get(filename);
        Charset cs = StandardCharsets.ISO_8859_1;
        String line;

        MP03 program = new MP03(filename);
        
        try(BufferedReader reader = Files.newBufferedReader(path, cs)){
            while((line = reader.readLine()) != null){
                String[] readLine = line.split(",");
                State temp = null;
                State next = null;
                
                temp = program.getState(readLine[0]);
                
                if(temp == null){
                    temp = new State(readLine[0]);
                    if(readLine[0].equals("start"))
                        program.setStart(temp);
                    else if(readLine[0].equals("accept"))
                        temp.setIsFinal(true);
                    
                    program.addState(temp);
                }
                
                switch (readLine[1]) {
                    case "left": temp.setDirection(0);
                                 break;
                    case "right": temp.setDirection(1);
                                  break;
                }
                
                Character symbol = readLine[2].charAt(0);
                next = program.getState(readLine[3]);
                
                if(next == null){
                    next = new State(readLine[3]);
                    if(readLine[3].equals("start"))
                        program.setStart(next);
                    else if(readLine[3].equals("accept"))
                        next.setIsFinal(true);
                    
                    program.addState(next);
                }
                
                temp.addTransition(symbol, next);
            }			
      	} catch(IOException x){
            System.err.println(x);
      	}
        
        return program;
    }
    
    public static ArrayList<String> readInput(String filename){        
        ArrayList<String> inputStrings = new ArrayList<String>();
        
        Path path = Paths.get(filename);
        Charset cs = StandardCharsets.ISO_8859_1;
        String temp;
		
        try(BufferedReader reader = Files.newBufferedReader(path, cs)){
            while((temp = reader.readLine()) != null){
                inputStrings.add(temp);
            }			
      	} catch(IOException x){
            System.err.println(x);
      	}
        
        return inputStrings;
    }
}
