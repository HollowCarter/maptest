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
				if (PlaPos >= 0 && PlaPos <= 999) {
					map = "Classic";
					mapCh = 1;
				} else if (PlaPos >= 1000 && PlaPos <= 1999){
					map = "Beach";
					mapCh = 1;
				}else if(PlaPos >= 2000 && PlaPos <= 2999) {
					map = "Highrise";
					mapCh = 1;
				}else if(PlaPos >= 3000 && PlaPos <= 3999) {
					map = "Medieval";
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