package ramadhans_coffee;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Payment> payments = new ArrayList();
        int pilih;
        Scanner sc = new Scanner( System.in ); 

        do{
            System.out.println("-----------------");
            System.out.println(" RAMADHANS COFFEE ");
            System.out.println("-----------------");
            System.out.println("1. Buy Coffee");
            System.out.println("2. Check Out");
            System.out.println("3. Exit");
            System.out.println("-----------------");
            System.out.print("Choose: ");
            pilih = sc.nextInt();
            
            if ( pilih == 1 ){
                payments = buyCoffee( payments );
            }else if( pilih == 2 ){
                payments = checkOut( payments );
            }
        } while ( pilih != 3 );
        
        payments.clear();
    }

    private static ArrayList<Payment> buyCoffee( ArrayList<Payment> p ) {
        Scanner sc = new Scanner( System.in ); 
        ArrayList<Payment> payments = new ArrayList();
        String name, type, sugar = null; 
        int qty;
        
        payments = p; //masukkan data payment
        
        System.out.print( "Input Name Coffe: " );
        name = sc.nextLine();

        boolean ok;
        do{
            System.out.print("Input Type Name [ Americano, Latte, Arabika ]: ");
            type = sc.nextLine();
            ok = cekKopi( type );
        }while(ok == false);

        do{
            System.out.print( "Added Sugar [ YES / NO ]: " );
            sugar = sc.nextLine();
            ok = cekGula( sugar );
        }while(ok == false);
        
        do{
            System.out.print( "Quantity [>0]: ");
            qty = sc.nextInt();
            ok = cekQty(qty);
        }while(ok == false);

        payments.add( new Payment( name, type, sugar, qty ) );
        
        return payments;
        
    }
    
    public static boolean cekKopi( String name ){
        boolean ok = false; 
        
        if ( name.equalsIgnoreCase( "Americano" ) || 
                name.equalsIgnoreCase( "Latte" ) ||
                name.equalsIgnoreCase( "Arabika" ) ){
            ok = true;
        }
        
        return ok;
    }
    
    private static boolean cekGula(String gula) {
        boolean ok = false; 
        
        if ( gula.equalsIgnoreCase( "YES" ) || 
             gula.equalsIgnoreCase( "NO" ) ){
            ok = true;
        }
        
        return ok; 
    }

    private static boolean cekQty(int qty) {
        boolean ok = false;
        
        if (qty >= 1){
            ok = true;
        }
        return ok;
    }
    
    private static boolean cekBayar(int total, int bayar) {
        boolean ok = false; 
        
        if ( bayar >= total ){
            ok = true;
        }
        
        return ok;
    }

    private static ArrayList<Payment> checkOut( ArrayList<Payment> p ) {
        ArrayList<Payment> payments = new ArrayList();
        int harga, jumlah, total, bayar;
        Scanner sc = new Scanner( System.in ); 

        payments = p;
                
        total = 0;
        
        System.out.println("Jumlah Order: "+ String.valueOf( payments.size() ) );
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|", 
                "No.", 
                "Name", 
                "Type", 
                "Extra", 
                "Qty", 
                "Price", 
                "Total");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        
        int num = 1;
        for( int i = 0; i < payments.size(); i++){
            harga = ( payments.get( i ).getQty() * payments.get( i ).getName().length() * 100 );   

            if ( payments.get( i ).getSugar().equals( "Y" ) ){
                jumlah =  payments.get( i ).getQty() * harga;
            }else{
                jumlah =  (int) (( payments.get( i ).getQty() * harga ) + ( payments.get( i ).getQty() * 0.03 ));
            }

            System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|", 
                num++, 
                payments.get( i ).getName(), 
                payments.get( i ).getType(), 
                payments.get( i ).getSugar(), 
                payments.get( i ).getQty(), 
                harga, 
                jumlah);

            System.out.println();

            total += jumlah;
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Total: "+ total);

        boolean ok;
        do{
            System.out.print("Bayar: ");
            bayar = sc.nextInt();
            ok = cekBayar( total, bayar );
        }while(ok == false);

        payments.clear();

        System.out.println("Kembalian: "+ abs(total - bayar) );
        System.out.println("Succesfully add new order!");
        System.out.println("Press enter to continue..");
        sc.nextLine();
        sc.nextLine();
        
        return payments;
    }
}
