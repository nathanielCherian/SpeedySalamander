package APFRQ.Unit8;

public class Plot {

    private String cropType;
    private int cropYield;

    public Plot(String crop, int yield){
        this.cropType = crop;
        this.cropYield = yield;
    }

    public String getCropType(){
        return cropType;
    }

    public int getCropYield(){
        return cropYield;
    }

    @Override
    public String toString() {
        return "Plot{"+cropType+" "+cropYield+"}";
    }

    private class ExperimentalFarm{

        private Plot[][] farmPlots;

        public ExperimentalFarm(Plot[][] p){
            this.farmPlots = p;
        }

        public Plot getHighestYield(String c){

            Plot maxPlot = null;
            for(Plot[] row: farmPlots){
                for(Plot plot: row){
                    if(plot.cropType.equals(c)){
                        int max=0;
                        if(maxPlot != null) max=maxPlot.getCropYield();
                        if(plot.cropYield > max) maxPlot=plot;
                    }
                }
            }

            return  maxPlot;
        }

    }


}
