package communication;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug; // Indicator of "debugging mode"

	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

		debug = false; // Default is to NOT be in debug mode
	}

	// TODO: Add writeByte() method that you created in Studio 5
	public void writeByte(byte b) {
		try {
			port.writeByte(b);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (debug == true) {
			System.out.print("<0x" + String.format("%02x", b) + ">");
		}
	}

	// TODO: Add readByte() method to read data from serial port
	public byte readByte() {
		byte b = 0;
		try {
			b = port.readBytes(1)[0];
		} catch (SerialPortException e) {
			e.printStackTrace();
		}

		if (debug) {
			System.out.print("[0x" + String.format("%02x", b) + "]");
		}

		return b;
	}

	// TODO: Add available() method to read data from serial port
	public boolean available() {
		try {
			return port.getInputBufferBytesCount() > 0;
		} catch (SerialPortException e) {
			e.printStackTrace();
			return false;
		}
	}

}
