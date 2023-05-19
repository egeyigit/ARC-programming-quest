
//Ege Yiğit Güven Programmin challenge
// en son soruyu yapamadım biraz zaman lazım bu simplex algoritmasını kafamda oturtmam için brute force ile yapılmaz


import java.util.Scanner;
import java.util.ArrayList;

public class ItemList {
    
        public static String [] itemnames = {"Encoder","Encoder Magnet","Polikarbon Plaka","Alüminyum 2024 Plaka","Alüminyum 4145 Plaka","Omniwheel","6 in. Wheel","4 in. Wheel","NavX","RoboRIO","Modem","Circuit Breaker","Robot Signal Light","16 AWG Wire", "18 AWG Wire"};
        public static String [] itemcodes =  {"4.1","4.2M","3.1","3.2-2","3.2-4","2.Omni","2.Omni","2\"6in\"","2\"4in\"","1/NavX","1/RIO","1/MODEM","5: CB","5: RSL","6.'16'","6.'18'"};
        public static Float[] itemprices = {20f,50f,200f,30f,50f,30f,20f,15f,200f,250f,50f,20f,80f,2.2f,0.75f};
        public static Float[] masses = {0.01f,0.03f,0.5f,3.5f,4.5f,0.4f,0.3f,0.2f,0.1f,1f,0.2f,1.5f,0.5f,0.25f,0.35f};
        public static String [] types = {"A","A","M2","M2","M2","A","A","A","A","A","A","A","A","m","m"};

        public static String[] getitemcodes(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("urun kodlarını gir aralarını tek virgül ile ayır: ");
            String input = scanner.nextLine();

            String[] words = input.split(",");


            Object[] objects = ItemList.itemcodes;
            String[] validWords = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                validWords[i] = (String) objects[i];
            }

            ArrayList<String> items = new ArrayList<String>();
            ArrayList<String> notvaliditems = new ArrayList<String>();
            boolean found = false;
            boolean foundone = false;
            for (String word : words) {
                found = false;

                for (String validWord : validWords) {
                    
                    if (word.trim().equals(validWord)) {
                        found = true;
                    } 
                    

                }

                if (!found) {
                    notvaliditems.add(word.trim());
                    foundone= true;
                } else {
                    items.add(word.trim());

                }
            }

            if (foundone) {
                System.out.print("Şu ürünlerin kodu yanlış tekrar deneyin");
                System.out.println(" ");
                for (String i: notvaliditems) {
                    System.out.println(i);
                }
                return getitemcodes();

            } else {
                return items.toArray(new String[items.size()]);
            }
        }



        public static int findstringindex(String[] array, String target) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(target)) {
                    return i;  
                }
            }
            return -1; 
        }

        public static Float[] getamount(String[] args){
            Scanner scanner = new Scanner(System.in);
            System.out.print("urunlerin sırayla miktarlarını gir aralarını tek virgül ile ayır: ");
            String input = scanner.nextLine();
            Float[] result = new Float[args.length];
            String[] words = input.split(",");
            
            try {
                if (words.length != args.length) {
                    System.out.println("lütfen ürün sayısında miktar girin.");
                    return getamount(args); 
                } 


                for (int i = 0; i < args.length; i++) {
                    
                        String word = words[i].trim();
                        Float num = 0f;
                        if (isNumeric(word)) {
                            num = Float.parseFloat(word);
                            

                            
                        
                        } else {
                            System.out.println("Geçersiz miktar: " + word);
                            return getamount(args); 

                        }
                        int itemindex = findstringindex(ItemList.itemcodes, args[i]);
                        
                        if (num% 1 != 0 && (ItemList.types[itemindex] == "A")){
                            System.out.println(ItemList.itemnames[itemindex] + " ürünü adetle satılmaktadır lütfen tam sayı girin"); 
                            return getamount(args); 
                        }


                        result[i] = num;
                       
                   
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz format, lütfen sayı giriniz");
                return getamount(args); // geçersiz format girildiğinde recursive function
            }
    
            return result;
        }
        
        public static Object[][] sort(int[] indexes, float[] values) {
            int length = indexes.length;
            Object[][] sortedArray = new Object[length][2];
        
            for (int i = 0; i < length; i++) {
                sortedArray[i][0] = values[i];
                sortedArray[i][1] = indexes[i];
            }
        
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    float firstNum = (float) sortedArray[i][0];
                    float secondNum = (float) sortedArray[j][0];
                    int firstIndex = (int) sortedArray[i][1];
                    int secondIndex = (int) sortedArray[j][1];
        
                    if (secondNum > firstNum) {
                        sortedArray[i][0] = secondNum;
                        sortedArray[j][0] = firstNum;
                        sortedArray[i][1] = secondIndex;
                        sortedArray[j][1] = firstIndex;
                    }
                }
            }
        
            return sortedArray;
        }

        //böyle bir şey yapmak ne kadar mantıklı bilmiyorum ama arraylist kullanınca datatypelar sıkıntı oluyordu
        public static int[] append(int[] array, int a){
            int[] finalarray = new int[array.length+1];
            for (int i = 0; i < array.length; i++) {
                finalarray[i] = array[i];
            }
            finalarray[array.length] = a;

            return finalarray;

        }

        public static void reverseArray(float[] array) {
            int start = 0;
            int end = array.length - 1;
        
            while (start < end) {
                float temp = array[start];
                array[start] = array[end];
                array[end] = temp;
        
                start++;
                end--;
            }
        }


        public static <T> void reverseArray(T[] array) {
            int start = 0;
            int end = array.length - 1;
        
            while (start < end) {
                T temp = array[start];
                array[start] = array[end];
                array[end] = temp;
        
                start++;
                end--;
            }
        }






        public static void calculateData(String[] items, Float[] amount){
            
            int[] indexes = new int[items.length];
            float[] totalprice = new float[items.length];
            float[] allmasses = new float[items.length];
            float[] itemmasses = new float[items.length];
            float[] itemamountsbyindex = new float[30];
            float robotmass = 0f;
            float totalcost = 0f;
            for (int i = 0; i < items.length; i++) {
                
                indexes[i] = findstringindex(ItemList.itemcodes, items[i]);
            }
            int indexcounter =  0;
            for ( int i: indexes) {
                itemamountsbyindex[i] = amount[indexcounter];
                indexcounter++;
            }

            for (int i = 0; i < items.length; i++) {
                float price =  ItemList.itemprices[indexes[i]];
                float mass = ItemList.masses[indexes[i]];
                totalprice[i] = (amount[i])*(price);
                totalcost += totalprice[i];
                allmasses[i] = (amount[i])*(mass);
                robotmass += allmasses[i];
                itemmasses[i] = mass;

            }

            Object[][] sortedData = sort(indexes, totalprice);


            System.out.println("seçtiğiniz ürünlerin pahalıdan ucuza toplam maliyetleri: ");

            for (int i = 0; i < sortedData.length; i++) {
                float sortedValue = (float) sortedData[i][0];
                int sortedIndex = (int) sortedData[i][1];
                System.out.println(ItemList.itemnames[sortedIndex]+" : "+sortedValue + " $");
            }
            
            Object[][] sortedmass = sort(indexes, allmasses);

            
            System.out.println("Hafiften Ağıra En Hafif 5 Malzemenin kod listesi:");

            // eğer 5ten az item varsa hepsini göster
            int startvalue = 0;

            if (sortedData.length >= 5){
                startvalue = sortedData.length-5;
            }



            for (int i = (sortedData.length-1); i >= startvalue; --i) {
                float sortedValue = (float) sortedmass[i][0];
                int sortedIndex = (int) sortedmass[i][1];
                System.out.println(ItemList.itemcodes[sortedIndex]+": "+sortedValue + " kg");
            }
            
            String compare = "";
            if (robotmass > 52.5){
                compare = " üstünde";
            } else {
                compare = " altında";
            }

            float extramass = Math.abs(52.f-robotmass);
            System.out.println("Robotun toplam ağırlığı: "+ robotmass +" kg"+ " 52.5 kilo limitinin " + extramass+ " kg "+ compare);
            System.out.println("Robotun toplam maliyeti: " + totalcost + " $");

            if (compare == " üstünde")     {
                // eğer m^2 ya da kg ile satılan malzemeler varsa direk oradan ne kadar çıkarılması gerektiği bulunabilir
    
                boolean aexist = false;
                int[] itemindexes = new int[0];
    
                int counter = 0;

                for (int i: indexes){

                    if (ItemList.types[i] != "A") {
                        //System.out.println("alan bulundu");
                        aexist = true;
                        itemindexes = append(itemindexes, counter);
                        
                    }
                    counter++;
                }
    
                if (aexist) {
                    int usedindex = -1;
                    for (int i: itemindexes) {
                        if (allmasses[i] > (robotmass-52.5f)){
                            usedindex = i;
                            break;
                        }
                        
                    }
                    if (usedindex != -1) {
                        float kilo = allmasses[usedindex];
                        float miktar = amount[usedindex];
                        
                        float subtractmiktar = ((robotmass-52.5f)*miktar)/kilo;
                        System.out.println(items[usedindex]+", "+kilo+" kg'dir "+(subtractmiktar)+" miktarında çıkarılması robotun sınırda kalmasını sağlar");
                    } else {
                        aexist = false;
                    }

                }
                // eğer sadece adet ile satılan ürünler varsa 
                if (!aexist) {
                    
                    Object[][] sorteditemmasses = sort(indexes, itemmasses);
                    Integer[] masskeys = new Integer[sorteditemmasses.length];
                    float[] sortedmasslist = new float[sorteditemmasses.length];
                    int[] itemstoremove = new int[sorteditemmasses.length];
                    for (int i = 0; i < sorteditemmasses.length; i++) {
                         masskeys[i] = (Integer) sorteditemmasses[i][1];
                         sortedmasslist[i]= (float) sorteditemmasses[i][0];
                        
                    }
                    reverseArray(masskeys);
                    reverseArray(sortedmasslist);

                    /*for(Integer i: masskeys){
                        System.out.println(i);
                    }
                    for(float i: sortedmasslist){
                        System.out.println(i);
                    }*/

                    // birbirine bölünmeyenleri bulalım böylece daha az ürün arasında kombinasyon hesabı yapmamız gerekecek
                    // araştırma yaptım biraz linear programmingle çözülüyormuş simplex algorithm, 
                    /*int numbers[] = new int[i];
                    
                    
                    float masses[] = new float[i];*/
                    /* 
                    float trials[] = new float[0];
                    int maximumnumber = ((int) (extramass / sortedmasslist[0]))+1;
                    for (int i = (sortedmasslist.length-1); i > 0; i--){
                        for (int b = (sortedmasslist.length-1); b > 0; b--){
                            if (sortedmasslist[i]%sortedmasslist[b] == 0){
                                sortedmasslist[i] = 0f;
                                masskeys[i] = -1;
                            }
                        }
                     

                    }

                    for(Integer i: masskeys){
                        System.out.println(i);
                    }
                    for(float i: sortedmasslist){
                        System.out.println(i);
                    }
                    */

                    System.out.println("kilo limitinin aşağısına inmesi için şu ürünler çıkarılmalıdır:");
                   int itemcount = 0;
                   while (extramass > 0f) {

                        itemamountsbyindex[masskeys[itemcount]] -= sortedmasslist[itemcount];
                        extramass -= sortedmasslist[itemcount];;
                        itemstoremove[itemcount] += 1;
                        if (itemamountsbyindex[masskeys[itemcount]] <= 0) {
                            itemcount ++;
                        }

                

                   }

                   int coun = 0;
                   for (int i: masskeys){
                    System.out.println("");
                   }
                }
            }
    
        }

        // girilen stringin bir sayı belirtip belirtmediğini kontrol et
        public static boolean isNumeric(String str) {
            return str.matches("-?\\d+(\\.\\d+)?");
        }

        public static void main(String[] args) {
            String[] items = getitemcodes();
            System.out.println("seçtiğiniz ürünler: ");
            for (String i: items){
                System.out.println(i);
            }
            Float [] amounts = getamount(items);
            
            calculateData(items, amounts);
        }

    
}


