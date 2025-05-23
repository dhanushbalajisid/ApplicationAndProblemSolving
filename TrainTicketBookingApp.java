import java.util.*;

public class TrainTicketBookingApp {
    public static Scanner obj=new Scanner(System.in);
    public static void main(String[] args) {
        mainmenu();
    }
    public static boolean flag=false;
    public static void mainmenu(){

        while (true) {
            System.out.println("1. Administrator\n2. Book Ticket\n3. Cancel Ticket\n4. Display ticket\n5. Exit");
            int userchoice=obj.nextInt();
            switch(userchoice) {
                case 1:
                    TrainDetails.admin();
                    break;
                case 2:
                    if(flag){
                        Userdetails.userdet();
                    }
                    else{
                        System.out.println("No trains are available");
                    }
                    break;
                case 3:
                    Userdetails.cancelTicket();
                    break;
                case 4:
                    System.out.println("Displaying Tickets");
                    TrainDetails.admin();
                    break;
                case 5:
                    System.out.print("Exiting");
                    for(int i=0;i<5;i++){
                        try {
                            System.out.print(".");
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e){
                            System.out.print("-----");
                        }

                    }
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}

class TrainDetails {

    private static Scanner obj = new Scanner(System.in);
    private static String adminuser = "administrator";
    private static String adminpassword = "admin123";

    public static void admin() {
        char ch='y';
        while (ch=='y') {
            obj.nextLine();
            System.out.print("Enter UserID : ");
            String adminuserid = obj.nextLine();
            System.out.print("Enter Password : ");
            String adminpass = obj.nextLine();

            if (adminuserid.equals(adminuser)) {
                if (adminpass.equals(adminpassword)) {
                    char chn='y';
                    while (chn=='y') {
                        System.out.println("1. Enter Train Details\n2. Remove Train Details\n3. Display Train Details\n4. Returning Mainmenu\n5. Exit");
                        int userchoice = obj.nextInt();
                        switch (userchoice) {
                            case 1:
                                System.out.println("Enter Train Details");
                                entertraindetails();
                                TrainTicketBookingApp.flag = true;
                                break;
                            case 2:
                                System.out.println("Removing Train");
                                removetraindet();
                                break;
                            case 3:
                                System.out.println("Displaying Train Details");
                                distraindetails();
                                break;
                            case 4:
                                System.out.println("Returning Main Menu");
                                TrainTicketBookingApp.mainmenu();
                                break;
                            case 5:
                                System.out.println("Exiting...");
                                chn = 'n';
                            default:
                                System.out.println("Invalid Input");
                                break;
                        }
                    }
                } else {
                    System.err.println("Wrong Password");
                }
            } else {
                if (!adminpass.equals(adminpassword)) {
                    System.err.print("Wrong Username and Password");
                } else {
                    System.err.print("Wrong Username");
                }
            }
        }
    }
    public static ArrayList<String[]>traindet = new ArrayList<>();
    public static ArrayList<Integer> trainnum = new ArrayList<>();
    public static ArrayList<String> traintime = new ArrayList<>();
    public static ArrayList<Integer> trainseat = new ArrayList<>();
    public static ArrayList<Integer> trainfair=new ArrayList<>();
    private static int id = 0;

    private static void entertraindetails() {
        char choice = 'Y';
        while (choice == 'Y') {
            System.out.println();
            System.out.print("Enter train number : ");
            int trainnumber = obj.nextInt();
            trainnum.add(trainnumber);
            obj.nextLine();
            System.out.print("Enter Source City : ");
            String trainsrc = obj.nextLine();
            System.out.print("Enter Destination city : ");
            String traindest = obj.nextLine();
            traindet.add(new String[]{trainsrc, traindest});
            System.out.print("Enter time : ");
            String time = obj.nextLine();
            traintime.add(time);
            id++;
            System.out.print("Enter seat capacity : ");
            int seats = obj.nextInt();
            trainseat.add(seats);
            System.out.print("Enter Fair : ");
            int fair = obj.nextInt();
            trainfair.add(fair);
            System.out.print("Do you want to add train details :(Y/N) ");
            choice = obj.next().toUpperCase().charAt(0);
        }
    }
    public static void removetraindet(){
        System.out.println("Enter train number: ");
        int trainnumber= obj.nextInt();
        try {
            int index = trainnum.indexOf(trainnumber);
            trainnum.remove(index);
            traindet.remove(index);
            trainseat.remove(index);
            traintime.remove(index);
            id--;
            System.out.println("Train Detailes Removed Successfully");
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("There are no trains available");
        }

    }
    public static void distraindetails() {
        try {
            System.out.println("Train number  Train Name  Seat Capacity  Time   Fair");
            System.out.println();
            for (int i = 0; i < id; i++) {
                System.out.print(trainnum.get(i) + "   ");
                String trainname[] = traindet.get(i);
                System.out.print(trainname[0] + "-" + trainname[1] + "  ");
                System.out.print(trainseat.get(i) + "    ");
                System.out.print(traintime.get(i) + "   ");
                System.out.print(trainfair.get(i) + "   ");
                System.out.println();
            }
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("No Trains Available");
        }
    }
}

class Userdetails{
    public static Scanner obj=new Scanner(System.in);

    public static void userdet(){
        System.out.println("1. Login\n2. Register\n3. Main Menu");
        int userchoice= obj.nextInt();
        switch (userchoice){
            case 1:
                if(login()){
                    System.out.print("Ticket Booking");
                    bookticket();
                }
                else{
                    System.out.println("No username or Password Found");
                    System.out.println("Kindly register");
                    register();
                }
                break;
            case 2:
                register();
                break;
            case 3:
                TrainTicketBookingApp.mainmenu();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
    public static ArrayList<String>useusername=new ArrayList<>();
    public static ArrayList<String> useuserpass=new ArrayList<>();

    public static boolean login(){
        obj.nextLine();
        System.out.print("Enter UserName : ");
        String usename=obj.nextLine();
        System.out.print("Enter Password : ");
        String usepass=obj.nextLine();
        return (useusername.contains(usename)&&useuserpass.contains(usepass));

    }
    public static void register(){
        obj.nextLine();
        System.out.print("Create username : ");
        String usename= obj.nextLine();
        useusername.add(usename);
        System.out.print("Create Password :");
        String usepass=obj.nextLine();
        useuserpass.add(usepass);
        System.out.println("Successfully Registered");
        userdet();
    }
    public static ArrayList<Integer>trainlist=new ArrayList<>();
    public static int fair=0;
    public static ArrayList<String>confirm=new ArrayList<>();
    public static ArrayList<String>rac=new ArrayList<>();
    public static ArrayList<String>wl=new ArrayList<>();
    public static int index=0;

    public static void bookticket(){
        obj.nextLine();
        System.out.print("Enter Source city :");
        String srccity=obj.nextLine();
        System.out.print("Enter Destination city : ");
        String destcity=obj.nextLine();
        for(int i=0;i<TrainDetails.traindet.size();i++){
            String scity=TrainDetails.traindet.get(i)[0];
            String dcity=TrainDetails.traindet.get(i)[1];
            if(srccity.equals(scity)&&destcity.equals(dcity)){
                trainlist.add(i);
            }
        }
        System.out.println("Train number   Train Name   Seat Capacity   Time   Fair");
        if(trainlist.size()>0) {
            for (int i = 0; i < trainlist.size(); i++) {
                System.out.print(TrainDetails.trainnum.get(trainlist.get(i)) + "   ");
                String trainname[] = TrainDetails.traindet.get(trainlist.get(i));
                System.out.print(trainname[0] + "-" + trainname[1] + "  ");
                System.out.print(TrainDetails.trainseat.get(trainlist.get(i)) + "    ");
                System.out.print(TrainDetails.traintime.get(trainlist.get(i)) + "   ");
                System.out.print(TrainDetails.trainfair.get(i) + "   ");
                System.out.println();
            }
        }else{
            System.out.println("No Trains Available in this Route");
            return;
        }
        System.out.print("Enter Train Number : ");
        int trainnumber= obj.nextInt();
        index=TrainDetails.trainnum.indexOf(trainnumber);
        if(index>=0) {
            System.out.print(TrainDetails.trainnum.get(index) + "   ");
            String trainname[] = TrainDetails.traindet.get(index);
            System.out.print(trainname[0] + "-" + trainname[1] + "  ");
            System.out.print(TrainDetails.trainseat.get(index) + "    ");
            System.out.print(TrainDetails.traintime.get(index) + "   ");
            System.out.print(TrainDetails.trainfair.get(index) + "   ");
        }
        else{
            System.out.println("Train number you entered is Invalid");
            return;
        }
        fair=TrainDetails.trainfair.get(index);
        System.out.print("Enter number of passengers : ");
        int pasnum= obj.nextInt();
        fair*=pasnum;
        obj.nextLine();
        userdetails(pasnum);
        System.out.println();
        if(payment()){
            int seat=TrainDetails.trainseat.get(index);
            for (int i=0;i<passdetails.size();i++) {
                String name = passdetails.get(i)[0];
                if (seat > 0) {
                    confirm.add(name);
                } else if (seat > -5) {
                    rac.add(name);
                } else if (seat > -10) {
                    wl.add(name);
                } else {
                    System.out.println("No more tickets available for " + name);
                    continue;
                }
                seat--;
            }
            TrainDetails.trainseat.set(index, seat);
        }
    }
    public static ArrayList<String[]> passdetails=new ArrayList<>();

    public static void userdetails(int pasnum){
        int i=1;
        passdetails.clear();
        while (pasnum>0) {
            System.out.println("Passenger "+i+" Details :");
            System.out.print("Enter your name : ");
            String pasname = obj.nextLine();
            System.out.print("Enter your age :");
            String pasage = obj.nextLine();
            System.out.print("Enter phone number : ");
            String pasphnum = obj.nextLine();
            passdetails.add(new String[]{pasname,pasage,pasphnum});
            pasnum--;
        }
    }

    public static String otp="";
    public static Random random=new Random();

    public static boolean payment(){
        otp="";
        for(int i=0;i<6;i++) {
            otp+=""+random.nextInt(9);
        }
        System.out.println("Your Otp is : "+otp);
        System.out.println("1.Google Pay\n2.Phone Pay");
        int payopt= obj.nextInt();
        System.out.print("Redirecting.....");
        int count=0;
        while(count<7) {
            System.out.print(".");
            count++;
            try{
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.err.println("Sorry for Inconvinence!!!");
            }
        }
        System.out.println();
        switch (payopt){
            case 1:
                System.out.println("Welcome to google pay!!");
                if(paymentGateway(fair)){
                    return true;
                }
                break;
            case 2:
                System.out.println("Welcome to Phone pay!!");
                if(paymentGateway(fair)){
                    return true;
                }
                break;
            default:
                System.out.println("No Payment Option available");
                break;
        }
        return false;
    }
    private static boolean paymentGateway(int price){
        System.out.println("Your Payable amount is: "+price);
        System.out.print("Enter otp: ");
        obj.nextLine();
        String userotp=obj.nextLine();
        if(otp.equals(userotp)){
            System.out.println("Otp Correct");
            System.out.println("Payment Successfull!!!");
            return true;
        }
        else{
            System.err.println("Otp Incorrect");
            System.err.println("Payment Unsuccessfull!!!");
            return false;
        }
    }

    public static void cancelTicket() {
        obj.nextLine();
        System.out.print("Enter passenger name to cancel ticket: ");
        String name = obj.nextLine();

        if (confirm.remove(name)) {
            System.out.println("Ticket cancelled from Confirmed list: " + name);
            if (!rac.isEmpty()) {
                String racToConfirm = rac.remove(0);
                confirm.add(racToConfirm);
                System.out.println("Moved from RAC to Confirmed: " + racToConfirm);
                if (!wl.isEmpty()) {
                    String wlToRac = wl.remove(0);
                    rac.add(wlToRac);
                    System.out.println("Moved from WL to RAC: " + wlToRac);
                }
            }
        } else if (rac.remove(name)) {
            System.out.println("Ticket cancelled from RAC list: " + name);
            if (!wl.isEmpty()) {
                String wlToRac = wl.remove(0);
                rac.add(wlToRac);
                System.out.println("Moved from WL to RAC: " + wlToRac);
            }
        } else if (wl.remove(name)) {
            System.out.println("Ticket cancelled from Waiting List: " + name);
        } else {
            System.out.println("No ticket found with the name: " + name);
        }
    }
}
