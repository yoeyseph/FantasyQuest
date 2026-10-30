package Projects1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class FantasyQuestGUI extends JFrame {

    private static final Color WINDOW_BACKGROUND = new Color(246, 243, 235);
    private static final Color PANEL_BACKGROUND = new Color(255, 253, 247);
    private static final Color TEXT_COLOR = new Color(48, 43, 37);
    private static final Color BORDER_COLOR = new Color(194, 183, 162);
    private static final Color FIGHT_RED = new Color(205, 112, 107);
    private static final Color FIGHT_RED_HOVER = new Color(216, 128, 122);
    private static final Color FIGHT_RED_PRESSED = new Color(184, 91, 87);

    private final GameAI gameAI;
    private final Player player;

    private final JLabel nextBattleLabel = new JLabel(" ");
    private final JLabel currencyStatusLabel = new JLabel();
    private final JLabel itemStatusLabel = new JLabel();
    private final JTextArea resultArea = new JTextArea(5, 34);
    private final JTextArea historyArea = new JTextArea(8, 34);
    private final JButton fightButton = new JButton("Fight Battle");

    public FantasyQuestGUI(GameAI gameAI, Player player) {
        super("Fantasy Quest — Monster Encounters");
        this.gameAI = gameAI;
        this.player = player;

        buildGui();
        updateNextBattleLabel();
        updatePlayerStatus();
        showMessage("Your adventure awaits. Choose an action to begin.");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(680, 570));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildGui() {
        setIconImage(createWindowIcon());

        JPanel mainPanel = new JPanel(new BorderLayout(14, 14));
        mainPanel.setBorder(new EmptyBorder(16, 18, 16, 18));
        mainPanel.setBackground(WINDOW_BACKGROUND);

        JPanel headerPanel = new JPanel(new BorderLayout(0, 8));
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("FANTASY QUEST", SwingConstants.CENTER);
        titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        titleLabel.setForeground(new Color(79, 63, 44));
        nextBattleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        nextBattleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nextBattleLabel.setForeground(TEXT_COLOR);
        nextBattleLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                new EmptyBorder(10, 12, 10, 12)));
        nextBattleLabel.setOpaque(true);
        nextBattleLabel.setBackground(new Color(239, 232, 216));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(nextBattleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 9, 9));
        buttonPanel.setOpaque(false);
        JButton itemsButton = new JButton("Check Items");
        JButton addMonstersButton = new JButton("Add Monsters");
        JButton newGameButton = new JButton("New Game");
        styleButton(fightButton, true);
        styleButton(itemsButton, false);
        styleButton(addMonstersButton, false);
        styleButton(newGameButton, false);

        fightButton.addActionListener(e -> fightBattle());
        itemsButton.addActionListener(e -> showMessage(player.displayItems()));
        addMonstersButton.addActionListener(e -> addMonsters());
        newGameButton.addActionListener(e -> startNewGame());

        buttonPanel.add(fightButton);
        buttonPanel.add(itemsButton);
        buttonPanel.add(addMonstersButton);
        buttonPanel.add(newGameButton);

        JPanel controls = new JPanel(new BorderLayout(0, 10));
        controls.setOpaque(false);
        controls.add(headerPanel, BorderLayout.NORTH);
        controls.add(buttonPanel, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new GridLayout(2, 1, 4, 4));
        statusPanel.setBackground(PANEL_BACKGROUND);
        statusPanel.setBorder(createPanelBorder("Adventurer Status"));
        currencyStatusLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        itemStatusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        currencyStatusLabel.setForeground(TEXT_COLOR);
        itemStatusLabel.setForeground(TEXT_COLOR);
        statusPanel.add(currencyStatusLabel);
        statusPanel.add(itemStatusLabel);

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        resultArea.setForeground(TEXT_COLOR);
        resultArea.setBackground(PANEL_BACKGROUND);
        resultArea.setMargin(new Insets(10, 11, 10, 11));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(createPanelBorder("Latest Update"));
        resultScroll.setPreferredSize(new Dimension(400, 130));

        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        historyArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        historyArea.setForeground(TEXT_COLOR);
        historyArea.setBackground(PANEL_BACKGROUND);
        historyArea.setMargin(new Insets(9, 10, 9, 10));
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(createPanelBorder("Battle History"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, resultScroll, historyScroll);
        splitPane.setResizeWeight(0.38);
        splitPane.setBorder(null);
        splitPane.setOpaque(false);
        splitPane.setDividerSize(8);

        JPanel lowerPanel = new JPanel(new BorderLayout(0, 10));
        lowerPanel.setOpaque(false);
        lowerPanel.add(statusPanel, BorderLayout.NORTH);
        lowerPanel.add(splitPane, BorderLayout.CENTER);

        mainPanel.add(controls, BorderLayout.NORTH);
        mainPanel.add(lowerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    private void styleButton(JButton button, boolean fight) {
        Color normal = fight ? FIGHT_RED : new Color(232, 226, 213);
        Color hover = fight ? FIGHT_RED_HOVER : new Color(241, 236, 226);
        Color pressed = fight ? FIGHT_RED_PRESSED : new Color(218, 209, 192);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        button.setForeground(fight ? new Color(50, 28, 27) : TEXT_COLOR);
        button.setBackground(normal);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                new EmptyBorder(8, 8, 8, 8)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) button.setBackground(hover);
            }
            @Override public void mouseExited(MouseEvent e) {
                button.setBackground(normal);
            }
            @Override public void mousePressed(MouseEvent e) {
                if (button.isEnabled()) button.setBackground(pressed);
            }
            @Override public void mouseReleased(MouseEvent e) {
                if (button.isEnabled()) button.setBackground(hover);
            }
        });
    }

    private javax.swing.border.Border createPanelBorder(String title) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR), title),
                new EmptyBorder(5, 5, 5, 5));
    }

    private void fightBattle() {
        Monster next = gameAI.getMonsterEncounters().peek();
        if (next == null) {
            String message = "No monsters remain. Add more monsters or start a new game.";
            showMessage(message);
            addHistory("No battle: the encounter queue is empty.");
            updateNextBattleLabel();
            return;
        }

        boolean won = gameAI.fightBattle(player.getCurrency(), player.getItems());
        if (won) {
            String message = next.getName() + " defeated!\n\n"
                    + "Rewards\n"
                    + "Currency (gold, silver, copper): " + next.getCurrency() + "\n"
                    + "Item: " + next.getItem();
            showMessage(message);
            addHistory("Victory: " + next.getName() + " | Currency: "
                    + next.getCurrency() + " | Item: " + next.getItem());
        } else {
            showMessage("There are no enemies nearby.");
            addHistory("No battle: the encounter queue is empty.");
        }
        updateNextBattleLabel();
        updatePlayerStatus();
    }

    private void addMonsters() {
        int previousCount = gameAI.getMonsterEncounters().size();
        gameAI.addRandom();
        int addedCount = gameAI.getMonsterEncounters().size() - previousCount;
        showMessage(addedCount + " new monsters have entered the encounter queue.");
        addHistory("Added " + addedCount + " monsters to the encounter queue.");
        updateNextBattleLabel();
    }

    private void startNewGame() {
        gameAI.getMonsterEncounters().clear();
        player.getCurrency().replaceAll(amount -> 0);
        player.getItems().clear();
        gameAI.addRandom();
        historyArea.setText("");
        showMessage("A new adventure begins. Your status and battle history have been reset.");
        updateNextBattleLabel();
        updatePlayerStatus();
    }

    private void showMessage(String message) {
        resultArea.setText(message);
        resultArea.setCaretPosition(0);
    }

    private void addHistory(String entry) {
        if (!historyArea.getText().isEmpty()) historyArea.append("\n\n");
        historyArea.append(entry);
        historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }

    private void updatePlayerStatus() {
        currencyStatusLabel.setText("Currency   " + player.displayCurrency().replace(" -- ", "     |     "));
        int itemCount = player.getItems().size();
        itemStatusLabel.setText("Items carried   " + itemCount
                + (itemCount == 1 ? " item" : " items"));
    }

    private void updateNextBattleLabel() {
        Monster next = gameAI.getMonsterEncounters().peek();
        if (next == null) {
            nextBattleLabel.setText("No enemies detected   ·   Add monsters to continue");
        } else {
            nextBattleLabel.setText("Next encounter   ·   " + next.getName());
        }
    }

    private Image createWindowIcon() {
        BufferedImage icon = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = icon.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(78, 62, 44));
        g.fillRoundRect(2, 2, 60, 60, 14, 14);
        g.setColor(new Color(232, 216, 184));
        g.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(20, 46, 45, 17);
        g.drawLine(16, 41, 27, 50);
        g.drawLine(39, 15, 48, 23);
        g.dispose();
        return icon;
    }

    public static void main(String[] args) {
        GameAI gameAI = new GameAI();
        gameAI.addRandom();
        Player player = new Player();
        SwingUtilities.invokeLater(() -> new FantasyQuestGUI(gameAI, player));
    }
}
