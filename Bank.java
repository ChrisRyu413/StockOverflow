import java.util.Scanner;

class Bank {
    public static void main(String[] args) {
        String[] users = new String[16];
        int account[] = new int[16];
        int usersize = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            if (commands[0].equals("create-user")) {
                if (usersize < 16) {
                    users[usersize++] = commands[1];
                    System.out.println("Welcome, " + commands[1]);
                } else
                    System.out.println("[ERROR] User overflow!");
            } else if (commands[0].equals("read-user")) {
                for (int i = 0; i < usersize; i++) {
                    if (users[i].equals(commands[1])) {
                        System.out.println(commands[1] + "'s ID = " + i);
                        break;
                    }
                }
            } else if(commands[0].equals("update-user")) {
            	boolean checkUser = false;
            	for(int i = 0; i < usersize; i++) {
            		if(users[i].equals(commands[1])) {
            			users[i] = commands[2];
            			System.out.println("Username has successfully changed!");
            			checkUser = true;
            			break;
            		}
            	}
            	if(!checkUser) 
            		System.out.println("Username doesn't exist!");
            } else if(commands[0].equals("delete-user")) { 
            	boolean checkUser = false;
            	for(int i = 0; i < usersize; i++) {
            		if(users[i].equals(commands[1])) {
            			checkUser = true;
            			for(int j = i; j < usersize - 1; j++) {
            				users[j] = users[j + 1];
            			}
            			System.out.println("User was successfully deleted.");
            			usersize--;
            			break;
            		}
            	}
            	if(!checkUser)
            		System.out.println("Username doesn't exist!");
            } else if(commands[0].equals("list-user")) {
            	System.out.println("Existing users are:");
            	for(int i = 0; i < usersize; i++) {
            		System.out.printf("ID: %d, USER: %s\n", i, users[i]);
            	}
            } else if(commands[0].equals("deposit")) {
            	int money = Integer.parseInt(commands[2]);
            	int location = 0;
            	boolean checkUser = false;
            	for (int i = 0; i < usersize; i++) {
                    if (users[i].equals(commands[1])) {
                    	checkUser = true;
                        location = i;
                        break;
                    }
                }
            	
            	if(checkUser) {
	            	account[location] += money;
	            	System.out.printf("%s's current balance is $%d.\n", commands[1], account[location]);
            	}else {
            		System.out.println("User not found.");
            	}
            } else if(commands[0].equals("withdraw")) {
            	int money = Integer.parseInt(commands[2]);
            	int location = 0;
            	boolean checkUser = false;
            	for (int i = 0; i < usersize; i++) {
                    if (users[i].equals(commands[1])) {
                    	checkUser = true;
                        location = i;
                        break;
                    }
                }
            	
            	if(checkUser) {
	            	account[location] -= money;
	            	System.out.printf("%s's current balance is $%d.\n", commands[1], account[location]);
            	}else {
            		System.out.println("User not found.");
            	}
            } else if(commands[0].equals("transfer")) {
            	int money = Integer.parseInt(commands[3]);
            	int loc1 = -1, loc2 = -1;
            	for (int i = 0; i < usersize; i++) {
                    if (users[i].equals(commands[1])) {
                        loc1 = i;
                    }
                    if(users[i].equals(commands[2])) {
                    	loc2 = i;
                    }
                }
            	
            	if(loc1 != -1 && loc2 != -1) {
            		if(account[loc1] >= money) {
            			account[loc1] -= money;
            			account[loc2] += money;
    	            	System.out.printf("%s's current balance is $%d.\n", commands[1], account[loc1]);
            		}
            		else {
            			System.out.println("You don't have enough money!");
            		}
            	}else {
            		System.out.println("User not found.");
            	}
            } else {
                break;
            }
        }

        scanner.close();
    }
}
