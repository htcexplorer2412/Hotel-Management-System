package Assignement1;
import java.util.*;

public class cityLodgeMain {

	public static void main(String[] args) {
		
       int choice, numberOfBed,roomIdStan=100,roomIdSuit=200,numOfRentDay,roomStatus=0;
       boolean x=true;
       String roomId, roomType,cusNo;
       String roomFeature="NULL";
       HotelRoom h1= new HotelRoom();
       DateTime date,rentDate,endDate;
       Scanner sc = new Scanner(System.in);
       
      while(x)
       {
       System.out.println("Please Enter your choice");  
       choice=sc.nextInt();
       sc.nextLine();
       switch (choice)
		
       {
	  case 1:
				
	  date=h1.date();
      System.out.println("Please Enter type of. Type R for Standard Room and Type S for Suit Room\n");
      String roomT=sc.nextLine();
      
	  if(roomT.equalsIgnoreCase("S"))
       {
    	   
    	   numberOfBed=6;
    	   roomIdSuit++;
    	   roomId= roomT+"_" +roomIdSuit; 
    	   roomId=roomId.toUpperCase();
    	   roomType="Standard";
    	   h1.addMethod(new HotelRoom(roomId, numberOfBed,roomFeature, roomType, roomStatus,date));
    	  
       }
	  else if (roomT.equalsIgnoreCase("R")) 
	  {	
      
	  do {
      System.out.println("please type number of Bed");
      numberOfBed=sc.nextInt();
		  }while(!(numberOfBed == 1 || numberOfBed == 2 || numberOfBed == 4));
		  
      roomIdStan++;
      roomId= roomT +"_"+ roomIdStan;
      roomId=roomId.toUpperCase();
      roomType= "Standard";
      h1.addMethod(new HotelRoom(roomId, numberOfBed,roomFeature, roomType, roomStatus,date));
      
	  }
	  else
	  {
		  System.out.println("Please Type valid Input");
	  }
	  
      break;
	        
	case 2:
	System.out.println("To rent a room, please Enter Room ID \n");
	roomId = sc.nextLine();
	roomId=roomId.toUpperCase();
	if(h1.checkId1(roomId))
	{
		if( h1.statusRoom(roomId) == 0)
		{
	    System.out.print("Please Enter Rent date ");
		rentDate=h1.date();
		System.out.println("Type customer ID");
		cusNo=sc.nextLine();
		System.out.println("How many Days?");
		numOfRentDay=sc.nextInt();
		
		//endDate=new DateTime(rentDate,numOfRentDay);
		if(h1.rent(roomId,cusNo, rentDate, numOfRentDay))
		{
			System.out.println("hello");
			System.out.println(rentDate);
			
			
		}
		
		}
		else
		{
		System.out.println("Room is not available at the moment");	
		}
	
	}
	else
	{
		System.out.println("please Write valid Room ID");
		

	}
	
	break;
	
	case 3:
		System.out.println("To return a Room, please provide roomId ");
		roomId=sc.nextLine();
		roomId=roomId.toUpperCase();
		
		
		if(h1.checkId1(roomId))
		{
			System.out.println("please write date of return");
			date=h1.date();
			h1.returnRoom(roomId,date);
		}
		
		else
		{
			System.out.println("Please provide valid ID");
		}
		break;
	
	 case 4:
		 
		 System.out.println("Please Type Date\n");
         date=h1.date();
		 System.out.println("To perform a maintenance, first provide RoomID ");
		 roomId=sc.nextLine();
		 roomId=roomId.toUpperCase();
		 if(h1.checkId1(roomId))
		 {
		 if(h1.performMaintenance(roomId,date))
		   {
			  
			 System.out.println(roomId+ " is now under maintenance");
		 
		   }
		 
		 }
		 else
		 {
			 System.out.println("provide valid ID");
		 }
		
		
			break;
		
	 case 5:
		 
		 System.out.println("To complete the maintainance, type RoomId");
		 roomId=sc.nextLine();
		 roomId=roomId.toUpperCase();
		 date=h1.date();
		 if(h1.completeMaintenance(roomId, date))
		 {
			 System.out.println(roomId+ " has all maintenance operations completed and is now ready for rent ");
		 }
		 
		  
		 
				
			}
	     }
      
      }
  }



