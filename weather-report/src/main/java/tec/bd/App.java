package tec.bd;

import picocli.CommandLine;
import tec.bd.cli.MainCommand;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "==> Weather Report" );

        CommandLine cmd = new CommandLine(new MainCommand());
        cmd.setExecutionStrategy(new CommandLine.RunAll()); // default is RunLast
        cmd.execute(args);

        if (args.length == 0) { cmd.usage(System.out); }
    }
}
