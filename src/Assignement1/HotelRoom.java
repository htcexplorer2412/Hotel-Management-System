package Assignement1;

import java.util.*;

public class HotelRoom {
	protected ArrayList<HotelRoom> room = new ArrayList<HotelRoom>(50);
	protected HashMap<String, DateTime[] > rentDates =new HashMap<String, DateTime[]>();
	protected HashMap<String, DateTime > maintenanceDate = new HashMap<String, DateTime>();
	protected HashMap<String, HotelRoom> room2 = new HashMap<String, HotelRoom>(50);
	protected int numberOfBed,day,month,year,roomStatus=0; //If roomStaus = 0 means it is Available, 1 means Rented, 2 means under Maintenance
	protected DateTime date,lastMaintenanceDate,newMaintenanceDate,actualReturn,estimatedReturn;
    protected String roomId,roomFeature,roomType;
	protected boolean roomStatus1;
	protected int x=0;
	protected double totalFee,lateFee,normalFee; 
	Scanner sc=new Scanner(System.in);

		
		public HotelRoom()
		{
			
		}
		public HotelRoom( String roomId, int numberOfBed,String roomfeature,String roomType, int roomStatus, DateTime date )
		{
		    this.roomId=roomId;
		  
		    this.date=date;
		    if(roomId.startsWith("S"))
		    {
		    	roomFeature="air conditioning, flat screen, cable TV, large seating area,outdoor balconies, gym";
		    	
		    }
		    else if(roomId.startsWith("R"))
		    {
		    	roomFeature="air conditioning, flat screen, cable TV";
		    }
			this.numberOfBed=numberOfBed;
			this.roomType = roomType;
			this.roomStatus=roomStatus;
			System.out.println(roomId+" has been created");
			
		}
		
		public void addMethod (HotelRoom h)
		{
			room.add(h);
			room2.put(h.roomId, h);
			maintenanceDate.put(h.roomId,h.date);
//			for(int i=0;i<room.size();i++)
//			{
//				
//			System.out.println( "Room no "+ room.get(i).roomId + " Created on "+ room.get(i).date);	
//			
//			}
			
			}
		
		
    	
			
			
		
		public void show()
		{
			//System.out.println(maintenanceDate.get("S_201")[0]);
		}
		
		public boolean checkId1 (String roomId)
		{
			this.roomId=roomId;
			
			/*if(room2.get(roomId2).roomId.equalsIgnoreCase(roomId2))
			{
				x++;
			}
			*/for(int i=0;i<room.size();i++)
		
			{
				
			if(room.get(i).roomId.equals(roomId))
				{
				  x++;
				}
		
			}
			if(x>=1)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		public DateTime date()
		{
			do
			{
			System.out.println("Please Enter a Year between 2012-2025(YYYY format)\n");
			year=sc.nextInt();}while(!(year > 2011 && year < 2026));
		    do
			{
		    System.out.println("Please Enter a Month between 1-12(MM format)\n");
			month=sc.nextInt();}while(!(month > 0 && month < 13)) ;
		    do
		    {
		    System.out.println("Please Enter a day between 1-31(DD format)\n");
			day=sc.nextInt();}while(!( day < 32 && day >0));
			
			return date;
			
			
		}
		
		public int statusRoom(String rooomId)
		{
			if(room2.get(roomId).roomStatus == 0)
			{
				return 0;
			}
			else if(room2.get(roomId).roomStatus == 1)
			{
				return 1;
			}
			else if(room2.get(roomId).roomStatus == 2)
			{
				return 2;
			}
			else
			{
				return 5;
			}
		}
		
		public boolean rent(String roomId, String cusId, DateTime rentDate, int numOfRentDay)
		{
			DateTime estimatedDate = new DateTime(rentDate,numOfRentDay);
	 		if(roomId.startsWith("R"))
	 		{
	 			System.out.println(rentDate);
	 			if((rentDate.getNameOfDay().equalsIgnoreCase("Saturday") || rentDate.getNameOfDay().equalsIgnoreCase("Sunday")) && numOfRentDay > 2 && numOfRentDay < 11)
	 			{
	 				room2.get(roomId).roomStatus=1;
	 				rentDates.put(roomId, new DateTime[] {rentDate,estimatedDate});
	 			    System.out.println(rentDates.get(roomId));
	 				return true;
	 				
	 			}
	 			else if(!(rentDate.getNameOfDay().equalsIgnoreCase("Saturday") || rentDate.getNameOfDay().equalsIgnoreCase("Sunday") && numOfRentDay > 1 && numOfRentDay <11) )
	 			{
	 					room2.get(roomId).roomStatus=1;	
	 					rentDates.put(roomId, new DateTime[] {rentDate,estimatedDate});
	 					return true;
	 			}
	 			else 
	 			{
	 				return false;
	 			}
	 			
	 		}
	 		
	 		else
	 		{
	 			DateTime startDate= new DateTime(rentDate,numOfRentDay);
 				DateTime endDate=new DateTime(maintenanceDate.get(roomId),10);
 				if(DateTime.diffDays(endDate, startDate) > 0)
 				{ 
 					room2.get(roomId).roomStatus=1;
 					return true;
 				}
 				else
 				{
 					return false;
 				}
 			}
		}
		
		public void returnRoom(String roomId,DateTime returnDate)
		{
			if(room2.get(roomId).roomStatus == 1)
			{
				System.out.println("Room has been return successfully");
				room2.get(roomId).roomStatus = 0;
				rentCalculater(roomId,returnDate);
				
			}
			else if(room2.get(roomId).roomStatus ==  2)
			{
				System.out.println("Room is under maintenance");
			}
			else
			{
				System.out.println("Please rent a room first");
			}
		}
		
		public void rentCalculater(String roomId, DateTime returnDate)
		{
			int difference= DateTime.diffDays(rentDates.get(roomId)[1],rentDates.get(roomId)[0]);
			int differenceLate=DateTime.diffDays(returnDate,rentDates.get(roomId)[1]);
			if(roomId.startsWith("R"))
			{
				
				if(room2.get(roomId).numberOfBed==1)
				{
					if(differenceLate>0)
					{
						lateFee=((59*135)/100)*differenceLate;
					}
					normalFee = difference*59;
					totalFee = normalFee + lateFee;
					System.out.println("Total Fee is "+ totalFee+"\n"+"Normal Fee is "+ normalFee + "\n"+"latefee Fee is "+ lateFee );
				
                    
				}
				else if(room2.get(roomId).numberOfBed==2)
				{
					if(differenceLate>0)
					{
						lateFee=((99*135)/100)*differenceLate;
					}
					normalFee = difference*99;
					totalFee = normalFee + lateFee;
					System.out.println("Total Fee is "+ totalFee+"\n"+"Normal Fee is "+ normalFee + "\n"+"latefee Fee is "+ lateFee );
				}
				else
				{
					if(differenceLate>0)
					{
						lateFee=((129*135)/100)*differenceLate;
					}
					normalFee = difference*129;
					totalFee = normalFee + lateFee;
					System.out.println("Total Fee is "+ totalFee+"\n"+"Normal Fee is "+ normalFee + "\n"+"latefee Fee is "+ lateFee );
					
				}
			}
			else
			{
				if(differenceLate>0)
				{
					lateFee=1099;
				}
				normalFee = difference*999;
				totalFee = normalFee + lateFee;
				System.out.println("Total Fee is "+ totalFee+"\n"+"Normal Fee is "+ normalFee + "\n"+"latefee Fee is"+ lateFee );
			}
		}
		
		
		public boolean performMaintenance(String roomId,DateTime date)
		{
			
			 if (checkId1(roomId))
				{
					if(statusRoom(roomId)== 0)
					{
						System.out.println("heer");
						if(DateTime.diffDays(date,maintenanceDate.get(roomId))>0)
						{
						
							room2.get(roomId).roomStatus = 2;
							return true;
					    }
						else
						{
							System.out.println("Please Enter Valid Date");
							return false;
						}
				    
					}
					
					else
					{
						
					System.out.println("Room is not available at the moment");
					return false;
					
					}
				
				}
			 
				else
					{
						System.out.println("Please Provide Valid ID");
						return false;
					}
			
		}
		
		public boolean completeMaintenance(String roomId, DateTime completionDate)
		{
			if (checkId1(roomId))
			{
				if(room2.get(roomId).roomStatus==2)
				{
					
					room2.get(roomId).roomStatus=0;
					maintenanceDate.put(roomId, completionDate);
					return true;
				}
				else 
				{
					System.out.println("Peforme a maintenance first");
					return false;
				}
				
			}
			else
			{
				return false;
			}
			
		}
		
		
	 
}
