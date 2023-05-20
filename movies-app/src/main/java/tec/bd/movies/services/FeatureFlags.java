package tec.bd.movies.services;

public class FeatureFlags {
    private boolean createMovieViaStoredProcedureEnabled = false;

    public boolean isCreateMovieViaStoredProcedureEnabled() {
        return this.createMovieViaStoredProcedureEnabled;
    }
    public void setCreateMovieViaStoredProcedureEnabled(boolean flag){
        this.createMovieViaStoredProcedureEnabled = flag;
    }

}
