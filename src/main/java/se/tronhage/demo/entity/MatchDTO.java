package se.tronhage.demo.entity;

public class MatchDTO{
    private final Long player1Id;
    private final Long player2Id;
    private final Long winnerId;

    public Long getPlayer1Id() {
        return player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public MatchDTO(Long player1Id, Long player2Id, Long winnerId) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.winnerId = winnerId;
    }
}
