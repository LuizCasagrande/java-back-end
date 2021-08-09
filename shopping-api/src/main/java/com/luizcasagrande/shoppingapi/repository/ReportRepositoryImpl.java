package com.luizcasagrande.shoppingapi.repository;

import com.luizcasagrande.shoppingapi.model.Shop;
import com.luizcasagrande.shoppingclient.dto.ShopReportDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        var sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.data >= :dataInicio ");

        if (dataFim != null) {
            sb.append("and s.data <= :dataFim ");
        }
        if (valorMinimo != null) {
            sb.append("and s.total >= :valorMinimo ");
        }

        TypedQuery<Shop> query = entityManager.createQuery(sb.toString(), Shop.class);
        query.setParameter("dataInicio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }
        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        String sb = """
                select count(s.id), sum(s.total), avg(s.total)
                from shopping.shop s
                where  s.data >= :dataInicio
                and s.data <= :dataFim
                """;
        Query query = entityManager.createNativeQuery(sb);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();
        var dto = new ShopReportDTO();
        dto.setCount(((BigInteger) result[0]).intValue());
        dto.setTotal((Double) result[1]);
        dto.setMean((Double) result[2]);

        return dto;
    }
}
