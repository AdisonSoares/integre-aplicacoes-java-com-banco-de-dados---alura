package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

public class ContaDAO {

	private Connection conn;

	ContaDAO(Connection connection) {
		this.conn = connection;
	}

	public void salvar(DadosAberturaConta dadosDaConta) {
		var cliente = new Cliente(dadosDaConta.dadosCliente());
		var conta = new Conta(dadosDaConta.numero(), BigDecimal.ZERO ,cliente, true);

		String sql = "INSERT INTO conta(numero, saldo, cliente_nome, cliente_cpf, cliente_email)"
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, conta.getNumero());
			preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
			preparedStatement.setString(3, cliente.getNome());
			preparedStatement.setString(4, cliente.getCpf());
			preparedStatement.setString(5, cliente.getEmail());
			preparedStatement.setBoolean(6, true);

			preparedStatement.execute();

			preparedStatement.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Conta> listar() {
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		Set<Conta> contas = new HashSet<>();
		String sql = "SELECT * FROM conta WHERE esta_ativa = true";

		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Integer numeroConta = resultSet.getInt(1);
				BigDecimal saldoConta = resultSet.getBigDecimal(2);
				String nomeCliente = resultSet.getString(3);
				String cpfCliente = resultSet.getString(4);
				String emailCliente = resultSet.getString(5);
				Boolean estaAtiva = resultSet.getBoolean(6);

				DadosCadastroCliente dadosCadastroCliente = 
						new DadosCadastroCliente(nomeCliente, cpfCliente,emailCliente);

				Cliente cliente = new Cliente(dadosCadastroCliente);

				contas.add(new Conta(numeroConta, saldoConta, cliente, estaAtiva));

			}

			resultSet.close();
			preparedStatement.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return contas;
	}
	
	public Conta listarPorNumero(Integer numero) {
        String sql = "SELECT * FROM conta WHERE numero = ? and esta_ativa = true";

        PreparedStatement ps;
        ResultSet resultSet;
        Conta conta = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numero);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer numeroRecuperado = resultSet.getInt(1);
                BigDecimal saldoConta = resultSet.getBigDecimal(2);
                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);
                Boolean estaAtiva = resultSet.getBoolean(6);

                DadosCadastroCliente dadosCadastroCliente =
                        new DadosCadastroCliente(nome, cpf, email);
                
                Cliente cliente = new Cliente(dadosCadastroCliente);

                conta = new Conta(numeroRecuperado, saldoConta, cliente, estaAtiva);
            }
            resultSet.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conta;
    }

	public void alterar(Integer numero, BigDecimal valor) {
		PreparedStatement preparedStatement;
		String sql = "UPDATE conta SET saldo = ? WHERE numero = ?";

		try 	{
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setBigDecimal(1, valor);
			preparedStatement.setInt(2, numero);
			
			preparedStatement.execute();

			preparedStatement.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer numeroDaConta) {
		PreparedStatement preparedStatement;
		String sql = "DELETE FROM conta WHERE numero = ?";

		try 	{
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, numeroDaConta);
			
			preparedStatement.execute();

			preparedStatement.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterarLogico(Integer numeroDaConta) {
		PreparedStatement preparedStatement;
		String sql = "UPDATE conta SET esta_ativo = false WHERE numero = ?";

		try 	{
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setInt(1, numeroDaConta);
			
			preparedStatement.execute();

			preparedStatement.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}


























