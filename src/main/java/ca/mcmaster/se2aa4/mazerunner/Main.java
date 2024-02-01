package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        try {
            Options options = new Options();
            options.addOption("i", true, "**** Reading the maze from file ");
            options.addOption("p", true, "");

            CommandLineParser parser = new DefaultParser();

            CommandLine cmd = parser.parse(options, args);
            String mazeFile = cmd.getOptionValue("i");
            Maze maze = new Maze(mazeFile);
            Walker walker = new Walker(maze);
            String path = cmd.getOptionValue("p");
            if(path==null || path.length()==0){
                String solution = walker.findPath();

                logger.info(solution);
            }
            else{
                int success = walker.checkPath(path, true);
                if(success==Walker.EXIT){
                    logger.info("correct path");
                }
                else{
                    logger.info("incorrect path");
                }
            }
        }

        catch(FileNotFoundException e ){
            logger.info("File Not Found");
        }
        catch(IOException e2){
            logger.info("Invalid File");
        }
        catch(Exception e3) {
            e3.printStackTrace();

            logger.error("/!\\ An error has occured /!\\");
        }

    }
}
