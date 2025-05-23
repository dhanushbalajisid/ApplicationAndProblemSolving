import java.util.*;
public class SwiggyApplication {
    public static void main(String[] args) {
        Mainmenu.mainmenu();
    }
}
class Mainmenu{
    public static void mainmenu(){
        System.out.println("*** Welcome to FoodDelivery Application ***\n");
        System.out.println("Enjoy doorstep delivery!!");
        System.out.println();
        Hotel.hotels();
    }
}
class Hotel{
    private static Scanner obj=new Scanner(System.in);
    public static int cho=0;
    public static void hotels(){
        ArrayList<String>hotelslist=new ArrayList<>();
        hotelslist.add("Buhari");
        hotelslist.add("A2B");
        hotelslist.add("Aasiffe");
        hotelslist.add("KFC");
        Dishes.dish();
        int i=1;
        for(String h:hotelslist){
            System.out.print(i++);
            System.out.println("."+h);
        }
        System.out.print("Enter your choice : ");
        int choice= obj.nextInt();
        cho=choice;
        switch (choice){
            case 1:
                Dishes.buhari();
                break;
            case 2:
                Dishes.A2B();
                break;
            case 3:
                Dishes.aasiffe();
                break;
            case 4:
                Dishes.kfc();
                break;
            default:
                System.out.print("Invalid Input");
        }
    }
}
class Dishes{
    public static List<String[]>dishes=new ArrayList<>();
    public static List<int []>prices=new ArrayList<>();
    public static List<int[]>stock=new ArrayList<>();
    public static Scanner obj=new Scanner(System.in);

    public static void dish(){
        dishes.add(new String[]{"Mutton Biriyani","Chicken Biriyani","Prawn Biriyani"});
        prices.add(new int[]{340,250,400});
        stock.add(new int[]{2,7,4});
        dishes.add(new String[]{"Dosa","Idly","Pongal","Poori"});
        prices.add(new int[]{60,25,40,70});
        stock.add(new int[]{25,40,10,15});
        dishes.add(new String[]{"Mutton Biriyani","Chicken Biriyani","Mutton Nalli Fry","Naatukozhi Fry"});
        prices.add(new int[]{410,290,400,350});
        stock.add(new int[]{12,23,10,5});
        dishes.add(new String[]{"Burger","Sand Wiches","Tenders","Fried Chicken"});
        prices.add(new int[]{240,150,200,250});
        stock.add(new int[]{15,23,12,24});
    }
    public static void buhari(){

        display(0);

    }
    public static void A2B(){

        display(1);
    }
    public static void aasiffe(){

        display(2);
    }
    public static void kfc(){

        display(3);
    }
    public static int stockj=0;
    private static void display(int j){
        stockj=j;
        String []d=dishes.get(j);
        int []p= prices.get(j);
        int []s=stock.get(j);
        for(int i=0;i<d.length;i++) {
            System.out.println((i+1)+"."+d[i]+" - "+p[i]);
        }
        order(d,p,s);
    }
    public static List<Integer> userfoods=new ArrayList<>();

    public static void order(String []dishes,int []prices,int []stocks){
        ArrayList<String> food=new ArrayList<>();
        ArrayList<Integer>foodprice=new ArrayList<>();
        List<Integer>dupdish=new ArrayList<>();
        int price=0;
        int cont=0;
        while (cont==0){
            System.out.print("Select your food : ");
            int userfood= obj.nextInt();
            userfoods.add(userfood);
            if(stocks[userfood-1]==0){
                System.out.println("Out of stock");
            }
            else {
                int val = 1;
                dupdish.add(val);
                if (food.contains(dishes[userfood - 1])) {
                    int dup = food.indexOf(dishes[userfood - 1]);
                    dupdish.add(dup, dupdish.get(dup) + 1);
                } else {
                    food.add(dishes[userfood - 1]);
                    foodprice.add(prices[userfood - 1]);
                }
                price += prices[userfood - 1];
                stocks[userfood - 1]--;
                stock.add(stocks);
            }
            System.out.print("Do you want to add food : Y/N : ");
            char choice=obj.next().toUpperCase().charAt(0);
            if(choice=='N'){
                cont=1;
                bill(food,foodprice,price,dupdish);
            }
        }
    }
    private static void bill(List<String> food,List<Integer>foodprice,int price,List<Integer>dupdish){
        System.out.println();
        System.out.println("   Your Bill  ");
        System.out.println("Dishes  Quantity  Prices");
        int quant=0;
        for(int i=0;i<food.size();i++){
            System.out.println(food.get(i)+"  "+dupdish.get(i)+"  "+foodprice.get(i));
            quant+=dupdish.get(i);
        }
        System.out.println();
        System.out.print("Quantity "+(quant)+"  Price : "+price);
        System.out.println();
        System.out.println("Select your Delivery Location ");
        int prices=loc();
        price+=prices;
        System.out.println("Your total bill is : "+price);
        System.out.println("Do you want to proceed to payment or cancel : Y/N ");
        char paymentOpt=obj.next().toUpperCase().charAt(0);
        switch (paymentOpt){
            case 'Y':
                Pay.payment(price);
                break;
            case 'N':
                System.out.println("Order Canceled");
                System.out.println("Thank you");
                cancelstocks();
                System.out.println("Returning Main Menu");
                Mainmenu.mainmenu();
                break;
        }
    }
    public static void cancelstocks(){
        int stocks[]=stock.get(stockj);
        for(int i:userfoods){
            stocks[i]+=1;
        }
        stock.add(stocks);
    }
    public static ArrayList<String> location=new ArrayList<>();
    public static int loc(){
        location.add("Ramya nagar");
        location.add("Anna nagar");
        location.add("T.nagar");
        location.add("R.K nagar");
        int i=1;
        System.out.println();
        for(String locations:location){
            System.out.println(i++ +" "+locations);
        }
        Scanner obj=new Scanner(System.in);
        int choice=Hotel.cho;
        System.out.print("Enter your location :");
        int locChoice= obj.nextInt();
        locChoice=choice-locChoice;
        int price=(((choice-locChoice<0)?locChoice*-1:locChoice)+1)*7;
        return price;
    }
}
class Pay extends Dishes{
    private static Random ran=new Random();
    private static Scanner obj=new Scanner(System.in);
    protected static String otp;

    public static void payment(int price){
        otp="";
        for(int i=0;i<6;i++) {
            otp+=""+ran.nextInt(9);
        }
        System.out.println("Your Otp is : "+otp);
        System.out.println("1.Google Pay\n2.Phone Pay");
        int payopt= obj.nextInt();
        System.out.print("Redirecting");
        int count=0;
        while(count<7) {
            System.out.print(".");
            count++;
            time();
        }
        System.out.println();
        switch (payopt){
            case 1:
                System.out.println("Welcome to google pay!!");
                paymentGateway(price);
                break;
            case 2:
                System.out.println("Welcome to Phone pay!!");
                paymentGateway(price);
                break;
            default:
                System.out.println("No Payment Option available");
                cancelstocks();
                System.out.println("Returning Main Menu!!");
                Mainmenu.mainmenu();
                break;
        }
    }
    private static void paymentGateway(int price){
        System.out.println("Your Payable amount is: "+price);
        System.out.print("Enter otp: ");
        obj.nextLine();
        String userotp=obj.nextLine();
        if(otp.equals(userotp)){
            System.out.println("Otp Correct");
            System.out.println("Payment Successfull!!!");
            System.out.println("Please wait!!");
            int n=5;
            for(int i=0;i<5;i++){
                System.out.println(".");
                time();
            }
            System.out.println("Order Placed");

            System.out.println("Returning Main Menu");
            Mainmenu.mainmenu();

        }
        else{
            System.err.println("Otp Incorrect");
            System.err.println("Payment Unsuccessfull!!!");
            cancelstocks();
            System.out.println("Returning Main Menu");
            Mainmenu.mainmenu();
        }
    }
    private static void time(){
        try{
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            System.out.println("Sorry for Inconvinience!");
        }
    }
}
