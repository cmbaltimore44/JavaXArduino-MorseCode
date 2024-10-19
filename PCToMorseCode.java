package communication;

import java.util.Scanner;

import jssc.SerialPortException;

public class PCToMorseCode {
	public static void main(String[] args) {		
		// TODO:  Fix this: 
		//           a) Uncomment to create a SerialComm object
		//           b) Update the "the port" to refer to the serial port you're using
		//              (The port listed in the Arduino IDE Tools>Port menu.		
		//           c) Deal with the unresolved issue
		SerialComm port;
		try {
			port = new SerialComm("/dev/cu.usbserial-110");
			port.setDebug(false);

			Scanner sc = new Scanner(System.in);
			
			while (true) {
                System.out.println("What would you like to encode?");
                String sentence = sc.nextLine();
                
                for (char c : sentence.toCharArray()) {
                    port.writeByte((byte) c);
                }
                port.writeByte((byte) '\n');

                System.out.print("\n\nMorse code: ");
                boolean messageComplete = false;

                while (!messageComplete) {
                    if (port.available()) {
                        byte received = port.readByte();
                        if ((char)received == '\n') {
                            messageComplete = true;
                        } else {
                            System.out.print((char) received);
                        }
                    }
                }

                System.out.println("\n");
            }
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
