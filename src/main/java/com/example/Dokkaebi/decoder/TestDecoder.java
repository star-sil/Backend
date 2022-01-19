package com.example.Dokkaebi.decoder;

import com.example.Dokkaebi.Repository.BatteryRepository;
import com.example.Dokkaebi.Repository.LocalRepository;
import com.example.Dokkaebi.domain.Battery;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDecoder extends ByteToMessageDecoder {
    private int DATA_LENGTH = 36;

    private final BatteryRepository Repo;





    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //읽을 수 있는 바이트가 있는지
        //capacity는 총 바이트 배열이다.
        if (in.readableBytes() < DATA_LENGTH) {
            log.info(in.toString(Charset.defaultCharset()));
            return;
        }
        String data = in.toString(Charset.defaultCharset());
        String stat = data.substring(0,2);
        String bike = data.substring(2,6);
        String soc = data.substring(6,8);
        String volt = data.substring(8, 11) + "." + data.substring(11,12);
        String temp = data.substring(12, 14);
        String lat = data.substring(14, 17) + "." + data.substring(17,22);
        String lon = data.substring(22, 25) + "." + data.substring(25,30);
        String pow = data.substring(30, 32);
        String shoc = data.substring(32, 34);

        short nbike = Short.valueOf(bike);
        String nstat = stat;
        int nsoc = Integer.valueOf(soc);
        double nvolt = Double.parseDouble(volt);
        BigDecimal nnvolt = BigDecimal.valueOf(nvolt);
        int ntemp = Integer.valueOf(temp);
        double nlat = Double.valueOf(lat);
        //DecimalFormat df = new DecimalFormat("000.0");
        BigDecimal nnlat = BigDecimal.valueOf(nlat);
        double nlon = Double.valueOf(lon);
        BigDecimal nnlon = BigDecimal.valueOf(nlon);
        Boolean npow = Boolean.valueOf(pow);
        Boolean nshoc = Boolean.valueOf(shoc);
        Battery battery = new Battery(nbike, nstat, nsoc,nnvolt, ntemp, nnlat, nnlon, npow, nshoc);
        if(battery != null)
        {
            Repo.save(battery);
        }
        log.info(in.toString(Charset.defaultCharset()));
        out.add(in.readBytes(DATA_LENGTH));
    }
}
