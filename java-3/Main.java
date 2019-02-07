/** Desafio codi:nation java-3
 * Processe dados dos jogadores do FIFA© 2017 usando Java
 * Autor: José Espinheira
 * joseespinheira@gmail.com
*/
package challenge;
import java.io.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
 

	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
class Jogador {
    public String[] datual;
    Date data = new Date();
    public String nome;
    public String data_nascimento;
    public float valor;
    SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    
    public Jogador(String nome, String data_nascimento, float valor) {
        this.nome = nome;
        this.valor = valor;
        this.data_nascimento = data_nascimento;
    }
    public String nome(){
        return this.nome;
    }
    public float valor(){
        return this.valor;
    }
    public String data_nascimento(){
        return this.data_nascimento;
    }
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
    public float idade(){
        String[] nascimento = data_nascimento.split("-");//data de nascimento do jogador
        datual = formatador.format(data).split("-");//data atual
        // calcula idade dos anos
        float ano = Integer.valueOf(datual[0])-Integer.valueOf(nascimento[0]);
        float meses = Integer.valueOf(datual[1])-Integer.valueOf(nascimento[1]);
        if(meses<0){
            ano--;
            meses=meses+12;
        }
        float dias = Integer.valueOf(datual[2])-Integer.valueOf(nascimento[2]);
        if(dias<0){
            meses--;
            dias=dias+30;
        }
        dias = dias + (meses*30);
        float dia;
        dia = ano + dias/1000;
        float floatValue=22.34555f;
        return dia;
    }
}

	 // Autor: José Espinheira
	 //joseespinheira@gmail.com

public class Main {
	private static final String VIRGULA = ",";
    private static final String ARQUIVOLEITURA = "src/main/resources/data.csv";
    
	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public int q1() {
        int count=0;
        try {
        	List<String> nacionalidade = new ArrayList<String>();//lista de nacionalidades diferentes
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
			String linha = null;
			linha = reader.readLine();
			String[] dadosUsuario = linha.split(VIRGULA);
			int i,j=0;
			while ((linha = reader.readLine())!=null) { //lendo documento
				dadosUsuario = linha.split(VIRGULA);    //organizando os valores
				int n = nacionalidade.size();
				for (i=0; i<n; i++) {
					//k++;
					if (dadosUsuario[14].equals(nacionalidade.get(i))){//verifica se a nacionalidade já existe
						j=1;
						break;
					}
				}
				if(j!=1){//não existe a nacionalidade
					nacionalidade.add(dadosUsuario[14]);
					count++;
				}
				j=0;
			}
			reader.close();
			
        }catch (FileNotFoundException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }catch (IOException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }
        return count;
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public int q2() {
		int count=0;
		try {
			List<String> clube = new ArrayList<String>();//lista de clubes diferentes
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
			String linha = null;
			linha = reader.readLine();
			String[] dadosUsuario = linha.split(VIRGULA);
			int i,j=0;
			while ((linha = reader.readLine())!=null) { //lendo documento
			    dadosUsuario = linha.split(VIRGULA);    //organizando os valores
			    int n = clube.size();
			    for (i=0; i<n; i++) {
			        //k++;
			        if (dadosUsuario[3].equals(clube.get(i)) || dadosUsuario[3].equals("")){//verifica se a clube já existe
			            j=1;
			            break;
			        }
			    }
			    if(j!=1){//não existe a clube
			        clube.add(dadosUsuario[3]);
			        count++;
			    }
			    j=0;
			}
			reader.close();
		}catch (FileNotFoundException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }catch (IOException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }
        return count;
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	//public static void main (String[] args) throws IOException {
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public List<String> q3() {
		List<String> items = new ArrayList<String>();
        
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
	        String linha = null;
	        linha = reader.readLine();
	        String[] dadosUsuario = linha.split(VIRGULA);
	        int n=1;
	        while (n<=20) {
	            linha = reader.readLine();
	            dadosUsuario = linha.split(VIRGULA);
	            if(dadosUsuario[2]!=null)
	                items.add(dadosUsuario[2]);
	            n++;
	        }
	        reader.close();
        }catch (FileNotFoundException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }catch (IOException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }
        return items;
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public List<String> q4() {
		List<String> result = new ArrayList();//lista de jogadoress diferentes
		try {
			List<Jogador> jogadores = new ArrayList();//lista de jogadoress diferentes
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
			String linha = null;
			linha = reader.readLine();
			String[] dadosUsuario = linha.split(VIRGULA);
			int i,j=0;
			while ((linha = reader.readLine())!=null) { //lendo documento
				dadosUsuario = linha.split(VIRGULA);    //organizando os valores
				//Procurar se a jogadores já existe na lista.
				int n = jogadores.size();
				for (i=0; i<n; i++) {
				    if (dadosUsuario[2].equals(jogadores.get(i).nome) || dadosUsuario[18].equals("") || dadosUsuario[2].equals("")){//verifica se a jogadores já existe
				        j=1;
				        break;
				    }
				    if(n>10){//tem um ou mais jogadores cadastrados
				        if( (Float.valueOf(dadosUsuario[18]).floatValue()) < jogadores.get(0).valor){//testa para deixar add somente os jogadores com melhores condições
				            j=1;
				            break;
				        }
				    }
				}
				if(j!=1  && jogadores!=null){//não existe o jogador ainda, então adiciona
					if(n>0){
						if((jogadores.size() < 10) || (Float.valueOf(dadosUsuario[18]).floatValue()) > jogadores.get(0).valor){//(jogadores.size() < 10) &&
							jogadores.add(new Jogador(dadosUsuario[2],"",Float.parseFloat(dadosUsuario[18])));
							Collections.sort (jogadores, new Comparator() {
								public int compare(Object o1, Object o2) {
									Jogador p1 = (Jogador) o1;
									Jogador p2 = (Jogador) o2;
									return p1.valor < p2.valor ? -1 : (p1.valor > p2.valor ? +1 : 0);
								}
							});
					    }
					}
				    else{
				        jogadores.add(new Jogador(dadosUsuario[2],"",Float.parseFloat(dadosUsuario[18])));
				    }
				}
				j=0;
			}
			reader.close();
			int n = jogadores.size();
			j= jogadores.size()-1;
			for (i=0; i<10; i++) {
			    result.add(jogadores.get(j).nome());//passando os valores para o vetor resultado
			    j--;
			}
		}catch (FileNotFoundException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }catch (IOException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        }
			return result;
		}
		
	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public List<String> q5() {
		//listaJogadores = processarArquivo.lerArquivo();
		List<String> result = new ArrayList<>();//lista de jogadoress diferentes
		try{
			// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
			// (utilize as colunas `full_name` e `birth_date`)
			
			List<Jogador> jogadores = new ArrayList<>();//lista de jogadoress diferentes
			//List<String> result = new ArrayList<>();//lista de jogadoress diferentes
			//List<String> valor = new ArrayList<String>();//lista de jogadoress diferentes
			int count=0;
			Date data = new Date();
			//SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
			String linha = null;
			linha = reader.readLine();
			String[] dadosUsuario = linha.split(VIRGULA);
			String[] nascimento = dadosUsuario[8].split("-");
			String[] datual;
			//System.out.println(linha);
			int i,j=0,k=0;
			while ((linha = reader.readLine())!=null) { //lendo documento
			    dadosUsuario = linha.split(VIRGULA);    //organizando os valores
			    nascimento = dadosUsuario[8].split("-");
			    
			    if(dadosUsuario[2]!="" || dadosUsuario[8]!=""){//só faz algo se existir nome completo e data de nascimento
			        // Add os 10 primeiros jogadores
			        int n = jogadores.size();
			        
			        if(n<11)//se tiver menos que 10 jogadores na lista
			        {
			            jogadores.add(new Jogador(dadosUsuario[2],dadosUsuario[8],0f));
			        }else{
			            //ordena por idade crescente
			            Collections.sort (jogadores, new Comparator() {
			                        public int compare(Object o1, Object o2) {
			                            Jogador p1 = (Jogador) o1;
			                            Jogador p2 = (Jogador) o2;
			                            return p1.idade() < p2.idade() ? -1 : (p1.idade() > p2.idade() ? +1 : 0);
			                        }
			            });
			            jogadores.add(new Jogador(dadosUsuario[2],dadosUsuario[8],0f));
			            if(jogadores.get(n-1).idade() > jogadores.get(0).idade()){
			                jogadores.remove(jogadores.get(0));
			            }
			        }
			    }
			}
			jogadores.remove(jogadores.get(jogadores.size()-1));
			for (i=0; i<jogadores.size(); i++) {
			    System.out.println(jogadores.get(i).data_nascimento() + " - " + jogadores.get(i).idade() + " - " + jogadores.get(i).nome());
			    //result.add(jogadores.get(j).nome());//passando os valores para o vetor resultado
			    //j--;
			}
			reader.close();
			int n = jogadores.size();
			j= jogadores.size()-1;
			for (i=0; i<10; i++) {
			    //System.out.println(jogadores.get(j).nome() +" "+jogadores.get(j).valor());
			    result.add(jogadores.get(j).nome());//passando os valores para o vetor resultado
			    j--;
			}
			for (i=0; i<10; i++) {
			    System.out.println(i+1 +" - "+result.get(i));
			}
		}catch (FileNotFoundException e) {
		    throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
		}catch (IOException e) {
		    throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
		}
			return result;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	 // Autor: José Espinheira
	 //joseespinheira@gmail.com
	public Map<Integer, Integer> q6() {
		Map<Integer, Integer> listaIdadesContador = new TreeMap<>();
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ARQUIVOLEITURA)));
	        String linha = null;
	        linha = reader.readLine();
	        String[] dadosUsuario = linha.split(VIRGULA);

	        int k;
	        while ((linha = reader.readLine())!=null) { //lendo documento
	            dadosUsuario = linha.split(VIRGULA);    //organizando os valores
	            int idade = Integer.valueOf(dadosUsuario[6]);
	            if(idade>0){//se idade do jogador for maior que 0;
	                if(listaIdadesContador.get(idade)==null){//se não existe o mapeamento
	                    listaIdadesContador.put(idade,1);//add o primeiro registros
	                }else{//add o contador do mapeamento
	                    k=listaIdadesContador.get(idade)+1;
	                    listaIdadesContador.put(idade,k);
	                }
	            }
	        }
	        reader.close();
			return listaIdadesContador;
        } catch (FileNotFoundException e) {
            throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
        } catch (IOException e) {
		    throw new Error("Ocorreu um erro ao abrir arquivo de leitura.");
		}
	}

}
/** Desafio codi:nation java-3
 * Processe dados dos jogadores do FIFA© 2017 usando Java
 * Autor: José Espinheira
 * joseespinheira@gmail.com
*/