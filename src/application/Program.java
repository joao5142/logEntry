package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entities.LogEntry;

public class Program {
	/*
	
		Um site de internet registra um log de acessos dos usuários. Um
		registro de log consiste no nome de usuário (apenas uma palavra) e o
		instante em que o usuário acessou o site no padrão ISO 8601,
		separados por espaço, conforme exemplo. Fazer um programa que leia
		o log de acessos a partir de um arquivo, e daí informe quantos usuários
		distintos acessaram o site.
	
	*/

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String path = System.getProperty("user.dir")+"//src//in.txt";
		
		//try with resources will'be close the bufferedreader automatically  
		try(BufferedReader br= new BufferedReader(new FileReader(path))){
			Set<LogEntry> set=new HashSet<>();
			
			String line=br.readLine();
			
			while(line != null) {
				String fields[]=line.split(" ");
				
				String username=fields[0];
				Date moment=Date.from(Instant.parse(fields[1]));
				set.add(new LogEntry(username,moment));
				
				line=br.readLine();
			}
			 
			System.out.println("Total users : "+set.size());
			set.forEach(System.out::println);
				
		}catch(IOException e) {
			System.out.println("Error message: "+e.getMessage());
		}
		sc.close();

	}

}
