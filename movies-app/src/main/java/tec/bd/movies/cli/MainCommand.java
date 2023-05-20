package tec.bd.movies.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import tec.bd.movies.cli.movies.CreateMovieCommand;
import tec.bd.movies.cli.movies.DeleteMovieCommand;
import tec.bd.movies.cli.movies.GetAllMoviesCommand;
import tec.bd.movies.cli.movies.GetMovieCommand;

@Command(
        name = "Movies App",
        subcommands = {
                GetAllMoviesCommand.class,
                GetMovieCommand.class,
                CreateMovieCommand.class,
                DeleteMovieCommand.class,
                HelpCommand.class
        },
        description = "Movie Catalog")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
