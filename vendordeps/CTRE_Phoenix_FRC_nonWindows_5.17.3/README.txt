The purpose of this document is to give instruction to manually install
CTRE Phoenix FRC Libraries.

BEFORE CONTINUING, INSTALL WPILIB AS DOCUMENTED HERE:
https://docs.wpilib.org/en/latest/

It is also highly recommended that you read the Phoenix documentation and follow those steps in order:
https://phoenix-documentation.readthedocs.io/en/latest/index.html



To Install Phoenix on your Workstation:

There are three folders contained in this archive-
- "maven"
- "vendordeps"
- "Robotbuilder"

For VSCode Integration:
Place the "maven" and "vendordeps" folders inside the WPILib installation folder.
This is typically located in ~/wpilib/2020.

Instructions on adding Phoenix to your FRC vscode project can be found here:
https://phoenix-documentation.readthedocs.io/en/latest/ch05a_CppJava.html


For Robotbuilder:
The Robotbuilder folder contains Robotbuilder extensions for using CTRE classes.
It currently contains the Talon SRX, Victor SPX, CANifier, and Pigeon IMU extensions.

Take the desired extension folder (eg. "Talon SRX") and place it in
~\wpilib\2020\Robotbuilder\extensions.