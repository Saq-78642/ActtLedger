package org.yearup;
import java.io.*;
import java.time.*;
import java.util.*;
import com.opencsv.*;


public class Main {
    private static final int asciiDifferential = 32;
    private static final char menuReset = 'Z';
    private static final short reportReset = 7;
    public static void main(String[] args) {
        Scanner menuChoice = new Scanner (System.in);
        //Sets variable to an option not found in menu to call prompt
        char topMenu = menuReset;
        char ledgerOption = menuReset;
        StringBuilder ledgerBuilder = new StringBuilder();
        while (true)
        {
            //Prompts user for top menu option if current option is not amongst valid options
            while (topMenu != 'D' && topMenu != 'P' && topMenu != 'L' && topMenu != 'X')
            {
                System.out.printf("Select a menu option: \n D) Add Deposit:\n P) Make Payment:\nL) Ledger:\nX) Exit\n");
                topMenu = menuChoice.next(".").charAt(0);
                //Forces valid chars to remain in uppercase
                if (topMenu >= 'a' && topMenu <= 'z')
                {
                    topMenu = (char) (topMenu - asciiDifferential);
                }
            }
            if (topMenu == 'X')
            {
                System.exit(0);
            }
            else if (topMenu == 'D')
            {
                System.out.printf("Deposit:\n" +
                        "Please enter one at a time pressing enter in betweem\n" +
                                "Description | Vendor (From whom?) | Amount (to credit account)\n");
                Scanner depChoice = new Scanner (System.in);
                String depositDesc = depChoice.nextLine();
                String depVendor = depChoice.nextLine();
                String depAmount = depChoice.nextLine();
                ledgerBuilder.append(LocalDate.now()).append("|").append(LocalTime.now()).append("|").append(depositDesc).append("|").append(depVendor).append("|").append(depAmount).append("\n");
                try (FileWriter fileWriter = new FileWriter("stablecoinLedger.csv",true) ) {

                    fileWriter.write(ledgerBuilder.toString());

                } catch(Exception uwu) {
                    uwu.printStackTrace();
                }
                topMenu = menuReset;
            }
            else if (topMenu == 'P')
            {
                System.out.printf("Deposit:\n" +
                        "Please enter one at a time pressing enter in betweem\n" +
                        "Description | Vendor (To whom?) | Amount (to debit account)\n");
                Scanner payChoice = new Scanner (System.in);
                String depositDesc = payChoice.nextLine();
                String depVendor = payChoice.nextLine();
                String depAmount = payChoice.nextLine();
                ledgerBuilder.append(LocalDate.now()).append("|").append(LocalTime.now()).append("|").append(depositDesc).append("|").append(depVendor).append("|-").append(depAmount).append("\n");
                try (FileWriter fileWriter = new FileWriter("stablecoinLedger.csv",true) ) {

                    fileWriter.write(ledgerBuilder.toString());

                } catch(Exception owo) {
                    owo.printStackTrace();
                }
                topMenu = menuReset;
            }
            else if (topMenu == 'L')
            {
                while (ledgerOption != 'A' && ledgerOption != 'D' && ledgerOption != 'P' && ledgerOption != 'R' && ledgerOption != 'H')
                {
                    System.out.printf("Ledger:\nA) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home\n");
                    topMenu = menuChoice.next(".").charAt(0);
                    if (ledgerOption >= 'a' && ledgerOption <= 'z')
                    {
                        ledgerOption = (char) (ledgerOption - asciiDifferential);
                    }
                }
                //Resets the values of the menus while breaking
                if (ledgerOption == 'H')
                {
                    topMenu = menuReset;
                    ledgerOption = menuReset;
                    break;
                }
                else if (ledgerOption == 'A')
                {try
                    {
                        CSVReader reader = new CSVReader(new FileReader("stablecoinLedger.csv"));
                    String[]line;
                        while((line = reader.readNext()) != null)
                        {
                            if (line != null)
                            {
                                System.out.println(Arrays.toString(line));
                            }
                        }
                    }
                    catch (Exception ohno)
                    {
                        System.out.println(ohno);
                    }
                }
                else if (ledgerOption == 'D')
                //Need to debug csvreader
                {
//                    try
//                {
//                    CSVReader reader = new CSVReader(new FileReader("stablecoinLedger.csv"));
//                    String[]line;
//                    String regex = "|-";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(line);
//                    while((line = reader.readNext()) != null)
//                    {
//                        if (line != null && matcher.find() == false))
//                        {
//                            System.out.println(Arrays.toString(line));
//                        }
//                    }
                }
//                catch (Exception ohno)
//                {
//                    System.out.println(ohno);
//                }
//                }
                else if (ledgerOption == 'P')
                // Need to debug CSV reader
                {
//                    try
//                    {
//                        CSVReader reader = new CSVReader(new FileReader("stablecoinLedger.csv"));
//                        String[]line;
//                        String regex = "|-";
//                        while((line = reader.readNext()) != null)
//                        {
//                            if (line != null && (line.matches("(.*)"+ regex + "(.*)"))
//                            {
//                                System.out.println(Arrays.toString(line));
//                            }
//                        }
//                    }
//                    catch (Exception ohno)
//                    {
//                        System.out.println(ohno);
//                    }
                }
                else if (ledgerOption == 'R')
                {
                    short reportChoice = 7;
                    Scanner reportScan = new Scanner (System.in);
                    while (reportChoice > 6 || reportChoice < 0)
                    {
                        System.out.printf("Reports:\n1: Month to Date\n2: Last Month\n3: Year to Date\n4: Previous Year\n" +
                                "5: Search by Vendor\n6: Custom Search\n0: Back\n");
                        reportChoice = reportScan.nextShort();
                    }
                    //List<Ledger> Entrylist = new ArrayList<>();
                    switch(reportChoice)
                    {
                        //COnsulted with Gregor regarding
                        case 0:
                            break;
                        case 1:
                            reportChoice = reportReset;
                            break;
                        case 2:
                            reportChoice = reportReset;
                            break;
                        case 3:
                            reportChoice = reportReset;
                            break;
                        case 4:
                            reportChoice = reportReset;
                            break;
                        case 5:
                            reportChoice = reportReset;
                            break;
                        case 6:
                            reportChoice = reportReset;
                            break;
                    }



                }

            }

        }

    }
}