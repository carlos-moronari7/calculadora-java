package com.example.calculadora_carlos_rodrigues_2023109;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView operTV,resulTV;
    MaterialButton botaoC,botaoAb,botaoFec;
    MaterialButton botaoDiv,botaoMult,botaoMais,botaoMenos,botaoIgual;
    MaterialButton botao1,botao2,botao3,botao4,botao5,botao6,botao7,botao9,botao0,botao8;
    MaterialButton botaoAC,botaoPt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operTV = findViewById(R.id.oper_tv);
        resulTV = findViewById(R.id.resultado_tv);
        DaId(botaoC,R.id.botao_c);
        DaId(botaoAb,R.id.botao_ab);
        DaId(botaoFec,R.id.botao_fe);
        DaId(botaoAC,R.id.botao_ac);
        DaId(botaoDiv,R.id.botao_div);
        DaId(botaoMult,R.id.botao_mult);
        DaId(botaoMais,R.id.botao_ad);
        DaId(botaoMenos,R.id.botao_sub);
        DaId(botaoIgual,R.id.botao_ig);
        DaId(botaoPt,R.id.botao_pt);
        DaId(botao0,R.id.botao_0);
        DaId(botao1,R.id.botao_1);
        DaId(botao2,R.id.botao_2);
        DaId(botao3,R.id.botao_3);
        DaId(botao4,R.id.botao_4);
        DaId(botao5,R.id.botao_5);
        DaId(botao6,R.id.botao_6);
        DaId(botao7,R.id.botao_7);
        DaId(botao8,R.id.botao_8);
        DaId(botao9,R.id.botao_9);
    }

    void DaId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    MaterialButton botao =(MaterialButton) v;
    String textobotao = botao.getText().toString();
    String calcular = operTV.getText().toString();

    if (textobotao.equals("AC")){
        operTV.setText("");
        resulTV.setText("0");
        return;
    }
    if (textobotao.equals("=")){
        resulTV.setText(resulTV.getText());
        return;
    }
    if (textobotao.equals("C")){
        calcular = calcular.substring(0,calcular.length()-1);
    }else{
        calcular = calcular + textobotao;
    }

    operTV.setText(calcular);
    String resultadoFinal = Resultado(calcular);

    if (!resultadoFinal.equals("ERRO")){
        resulTV.setText(resultadoFinal);
        }
    }
    String Resultado(String calculo){

        try {
            Context contexto = Context.enter();
            contexto.setOptimizationLevel(-1);
            Scriptable script = contexto.initStandardObjects();
            String resultadoFinal = contexto.evaluateString(script,calculo,"Javascript",1,null).toString();
            if (resultadoFinal.endsWith(".0")){
                resultadoFinal = resultadoFinal.replace(".0","");
            }
            return resultadoFinal;
        }catch (Exception e){
            return "ERRO";
        }
    }
}