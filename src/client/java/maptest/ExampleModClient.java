package maptest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ExampleModClient implements ClientModInitializer {

	public String message = "";
	public String map = "";
	public Integer PlaPos = 5000;
	public Integer mapCh = 0;


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientTickEvents.END_CLIENT_TICK.register(client ->{
			PlayerEntity player = client.player;
			if (player != null){
				BlockPos playerXYZ = player.getBlockPos();
				PlaPos = playerXYZ.getX();
			}
			if (mapCh == 1){
			client.player.sendMessage(Text.literal(map),false);
			}
		});

		ClientReceiveMessageEvents.GAME.register((mess, over)->{message = mess.getString();
            if (message.contains ("Game Started!")){ 
				if (PlaPos >= 249 && PlaPos <= 262) {
					map = "Map: Classic";
					mapCh = 1;
				} else if (PlaPos >= 762 && PlaPos <= 775){
					map = "Map: Fishbowl";
					mapCh = 1;
				}else if (PlaPos >= 1274 && PlaPos <= 1287){
					map = "Map: Beach";
					mapCh = 1;
				}else if(PlaPos >= 2318 && PlaPos <= 2331) {
					map = "Map: Highrise";
					mapCh = 1;
				}else if(PlaPos >= 3327 && PlaPos <= 3340) {
					map = "Map: Medieval";
					mapCh = 1;
				}
				System.out.println(map);
            }else {
				map = "";
				mapCh = 0;
			}
        });
		
	}
}