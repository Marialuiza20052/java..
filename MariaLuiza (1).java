import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GerenciamentoUsuariosTela extends JFrame {
    public GerenciamentoUsuariosTela() {
        setTitle("Gerenciamento de Usuários");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5,5));

        DefaultListModel<String> model = new DefaultListModel<>();
        BibliotecaApp.usuarios.forEach(model::addElement);

        JList<String> listaUsuarios = new JList<>(model);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);

        JPanel botoesPanel = new JPanel(new FlowLayout());

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");
        JButton btnFechar = new JButton("Fechar");

        btnAdicionar.addActionListener(e -> {
            String novoUsuario = JOptionPane.showInputDialog(this, "Nome do novo usuário:");
            if(novoUsuario != null && !novoUsuario.trim().isEmpty()) {
                if (!BibliotecaApp.usuarios.contains(novoUsuario.trim())) {
                    BibliotecaApp.usuarios.add(novoUsuario.trim());
                    model.addElement(novoUsuario.trim());
                    JOptionPane.showMessageDialog(this, "Usuário adicionado!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário já existe.");
                }
            }
        });

        btnRemover.addActionListener(e -> {
            int idx = listaUsuarios.getSelectedIndex();
            if(idx >= 0) {
                int resp = JOptionPane.showConfirmDialog(this, "Remover usuário selecionado?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION) {
                    BibliotecaApp.usuarios.remove(idx);
                    model.remove(idx);
                    JOptionPane.showMessageDialog(this, "Usuário removido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
            }
        });

        btnFechar.addActionListener(e -> dispose());

        botoesPanel.add(btnAdicionar);
        botoesPanel.add(btnRemover);
        botoesPanel.add(btnFechar);

        add(scrollPane, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

class CadastroCategoriasTela extends JFrame {
    public CadastroCategoriasTela() {
        setTitle("Cadastro de Categorias");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Nova Categoria:"));
        JTextField campoCategoria = new JTextField(25);
        add(campoCategoria);

        JButton btnAdicionar = new JButton("Adicionar Categoria");
        add(btnAdicionar);

        JTextArea listaCategorias = new JTextArea(8, 25);
        listaCategorias.setEditable(false);
        updateLista(listaCategorias);
        add(new JScrollPane(listaCategorias));

        btnAdicionar.addActionListener(e -> {
            String novaCat = campoCategoria.getText().trim();
            if(!novaCat.isEmpty()) {
                if(!BibliotecaApp.categorias.contains(novaCat)) {
                    BibliotecaApp.categorias.add(novaCat);
                    updateLista(listaCategorias);
                    campoCategoria.setText("");
                    JOptionPane.showMessageDialog(this, "Categoria adicionada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Categoria já existe.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Digite uma categoria válida.");
            }
        });

        setVisible(true);
    }

    private void updateLista(JTextArea area) {
        area.setText("");
        BibliotecaApp.categorias.forEach(cat -> area.append(cat + "\n"));
    }
}

class EstatisticasTela extends JFrame {
    public EstatisticasTela() {
        setTitle("Estatísticas da Biblioteca");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JTextArea area = new JTextArea();
        area.setEditable(false);

        StringBuilder stats = new StringBuilder();
        stats.append("Total de Livros: ").append(BibliotecaApp.livros.size()).append("\n");
        stats.append("Total de Usuários: ").append(BibliotecaApp.usuarios.size()).append("\n");
        stats.append("Total de Categorias: ").append(BibliotecaApp.categorias.size()).append("\n");
        stats.append("Total de Empréstimos/Devoluções: ").append(BibliotecaApp.historicoEmprestimos.size()).append("\n");

        area.setText(stats.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(btnFechar);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
