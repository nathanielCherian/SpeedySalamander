package Game.Listeners;

import Game.Objects.Coin;

public interface CoinCollectListener extends Listener{
    public void onCollectCoin(Coin coin);
}
